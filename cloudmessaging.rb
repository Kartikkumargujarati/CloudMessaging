require 'sinatra'
require 'rest-client'
require 'sequel'

# Create a SQLite3 database

DB = Sequel.connect('sqlite://myfcm.db')

# Create a Device table if it doesn't exist
DB.create_table? :Device do
  primary_key :reg_id
  String :user_id
  String :fcmreg_token
  String :os, :default => 'android'
end

Device = DB[:Device]  # create the dataset

# Registration endpoint mapping fcmreg_token to user_id
# POST /register?fcmreg_token=abc&user_id=123
post '/register' do
  if Device.filter(:fcmreg_token => params[:fcmreg_token]).count == 0
    device = Device.insert(:fcmreg_token => params[:fcmreg_token], :user_id => params[:user_id], :os => 'android')
  end
end

# Ennpoint for sending a message to a user
# POST /send?user_id=123&title=hello&body=message
post '/send' do
  # Find devices with the corresponding reg_tokens
  reg_tokens = Device.filter(:user_id => params[:user_id]).map(:fcmreg_token).to_a
  if reg_tokens.count != 0
    send_fcm_message(params[:title], params[:body], reg_tokens)
  end
end

# Sending logic
# send_gcm_message(["abc", "cdf"])
def send_fcm_message(title, body, reg_tokens)
  # Construct JSON payload
  post_args = {
    # :to field can also be used if there is only 1 reg token to send
    :registration_ids => reg_tokens,
    :data => {
      :title  => title,
      :body => body,
      :anything => "foobar"
    }
  }


  # Send the request with JSON args and headers
  RestClient.post 'https://fcm.googleapis.com/fcm/send', post_args.to_json,
    :Authorization => 'key=' + 'API_KEY', :content_type => :json, :accept => :json

end

#steps to run:
#1. From Terminal start ruby server by "ruby cloudmessaging.rb". This starts the ruby server with cloudmessaging.rb file.
#2. From terminal, do:
#    curl http://localhost:4567/register -d "reg_token=FCM_TOKEN&user_id=111"
#3. From terminal do:
#.   curl http://localhost:4567/send -d "user_id=111&title=Notificaiton Title&body=Notification Message Body."

















