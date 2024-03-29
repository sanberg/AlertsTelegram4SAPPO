# AlertsTelegram4SAPPO

<img src="https://user-images.githubusercontent.com/7569642/207464957-79ad2cd9-c4d9-446b-8caa-1e55a48f1ac1.png" width="30%" height="30%" />

Message-Driven Bean implementation class for TelegramAlertingJob in SAP PI/PO. Sends messages from standard JMS queue *alertingVP* to Telegram channel via bot.

## Telegram bot & channel settings

1. Create public channel with public link (@nickname)
2. Create bot via [@botfather](https://t.me/BotFather)
3. Add bot to channel as admin

## How to config job in SAP PO

1. *Operations->Jobs* and open *Java Scheduler*
    
![Java Scheduler](https://user-images.githubusercontent.com/7569642/226341214-16476bc1-a3e9-47d6-b690-0e8b5e81e4f6.png)

2. On *Java Scheduler* screen go to tab *Tasks* and click the *Add* button
    
![Tasks](https://user-images.githubusercontent.com/7569642/226341317-2a797efb-c982-4ec2-a7fb-f9452f0c371f.png)

3. Select TelegramAlertingJob

![image](https://user-images.githubusercontent.com/7569642/207358318-f7d7dd3f-61e6-4e9a-aff0-c9605c39af0d.png)

4. Enter Job Parametres    

![image](https://user-images.githubusercontent.com/7569642/207359407-0c241b4d-9214-42b4-892c-4e3d0c7067c4.png)

*alertConsumer*: name of a consumer that was set in SAP PO Alert Rule. 
This is used as a name for JMS queue in alertingVP.

*maxAlertCount* is max count of consumed alerts from JMS queue

*apiToken*: can be retrieved from BotFather; 

*chatId*: id of telegram channel (described below in *How to get id of private telegram channel*) 

5. Do not forget to set back channel as private one unless you want everyone to see a mess in your system ^^
6. Set timer parameters. Running job every 2 min would be enough and much more representative than emails.

![image](https://user-images.githubusercontent.com/7569642/207364908-e8e73ec7-2e1f-45dc-95ee-4481c43fff73.png)

7. Finish the job configuration. As a result you will find a job on Tasks tab

![image](https://user-images.githubusercontent.com/7569642/207365733-63daeb4a-bab0-498d-a1ce-4457e5e2e06f.png)



## How to get id of private telegram channel 

- Add bot as administrator to channel
- Set channel as Public channel and set channel nickname like @nickname
- Send message using https request: https://api.telegram.org/{botToken}/sendMessage?chat_id=@{channelNickName}&text=*test*&parse_mode=Markdown 

As a result you will get JSON including real chat id:

```json
{
    "ok": true,
    "result": {
        "chat": {
            "id": -100187777,
            "title": "SAP PO Critical Alerts",
            "type": "channel",
            "username": "nickname"
        },
        "date": 1667811418,
        "entities": [
            {
                "length": 4,
                "offset": 0,
                "type": "bold"
            }
        ],
        "message_id": 4,
        "sender_chat": {
            "id": -100187777,
            "title": "SAP PO Critical Alerts",
            "type": "channel",
            "username": "nickname"
        },
        "text": "test"
    }
}
```
