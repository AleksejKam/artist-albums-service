**Top5 albums of my favourite artist**

A user can select one favourite artist and see top5 albums of that artist. The app will have two
pages:
1. Search and save a favourite artist
2. View top5 albums of a selected favourite artist

Store only List of amgArtistId for userId:
- GET     `http://localhost:8080/users/{userId}/favorite-artists`
- POST    `http://localhost:8080/users/{userId}/favorite-artists/{amgArtistId}`
- DELETE  `http://localhost:8080/users/{userId}/favorite-artists/{amgArtistId}`

Store top5 albums for amgArtistId. Refresh albums after 12 hours from https://itunes.apple.com
- GET     `http://localhost:8080/artists/{amgArtistId}/top-five-albums`

---------------------
iTunes API in a nutshell:
- Searching for artists by keyword: POST
https://itunes.apple.com/search?entity=allArtist&term=abba
- Getting top5 albums by AMG artist id: POST
https://itunes.apple.com/lookup?amgArtistId=3492&entity=album&limit=5
- More:
https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-sear
ch-api/