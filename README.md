# AlertsTelegram4SAPPO


## How to config job in SAP PO

1. *Operations->Jobs* and open *Java Scheduler*
    
![NWA_1](https://user-images.githubusercontent.com/7569642/207361440-127bc192-8ba2-460d-b735-73b575554b49.png)


2. On *Java Scheduler* screen go to tab *Tasks* and click the *Add* button
    
![NWA_2](https://user-images.githubusercontent.com/7569642/207361501-b9d6cf7e-45ef-4ade-b66d-43701a620d56.png)

3. Select TelegramAlertingJob

![image](https://user-images.githubusercontent.com/7569642/207358318-f7d7dd3f-61e6-4e9a-aff0-c9605c39af0d.png)

4. Enter Job Parametres    

![image](https://user-images.githubusercontent.com/7569642/207359407-0c241b4d-9214-42b4-892c-4e3d0c7067c4.png)

*alertConsumer*: name of a consumer that was set in SAP PO Alert Rule. 
This is used as a name for JMS queue in alertingVP.

*maxAlertCount* is max count of consumed alerts from JMS queue

*apiToken* - can be retrieved from BotFather; 

*chatId* - id of telegram channel

You can get it this way: 

- Add bot as administrator to channel
- Set channel as Public channel and set channel nickname
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

5. Do not forget to set back channel as private one.
6. Set timer parameters. Running job every 2 min would be enough and much more representative than emails.

![image](https://user-images.githubusercontent.com/7569642/207364908-e8e73ec7-2e1f-45dc-95ee-4481c43fff73.png)


7. Finish the job configuration. As a result you will find a job on Tasks tab

![image](https://user-images.githubusercontent.com/7569642/207365733-63daeb4a-bab0-498d-a1ce-4457e5e2e06f.png)

