# Giantbomb API

## API Keys

### Standard User Keys

Hali's Key `266edfe59de08f1e8e42e36ac1cc34b3e44ff766`

### Premium Keys

My Key `9370044ca3820c6695420ea259a2c484730ff5a2`

## Video Buddy Contact

Hi Kyle,

I'd be happy to help.  Getting the API key is actually really simple once you've got a link code; you just need to send a request to

`https://www.giantbomb.com/api/validate?link_code=XXXXXX`

...and, you'll either get the user's API key or null if the link code was invalid.  
Like the other API calls, it defaults to an XML response, but you can append `&format=json` if you prefer.

Depending on what your app will do, it may be useful to know if the user is a premium member.  
The API key doesn't inherently tell you anything about their subscriber status, but based on the response to certain API calls you can infer it.  

In GBVB I use the fact that only premium members are served the hd_url field for videos, so executing a query like

`https://www.giantbomb.com/api/video/2300-8685/?api_key=XXXXXX&field_list=hd_url`

...tells me if the user is currently paid up.

There are a couple of other things worth noting:
This auth method is undocumented and Giant Bomb hasn't ever made a promise to keep it working.  
It's been stable over GBVB's lifespan, and changing it would break the Boxee app, so I consider it pretty safe, but there's always a chance something could change.
You should do you best to keep the user's API key safe.  
It's not sensitive in the same way a password is, but GB does reserve the right to revoke abused API keys, so storing it somewhere private (like SharedPreferences) and using HTTPS are good ideas.
Since premium memberships run out, if you care about subscriber status you'll want to periodically re-run your is-this-user-premium check.
In case you were curious, I found the validate API endpoint by looking through the source of the official set-top box apps.
Best of luck!

-Chris

## Forum Links

[Quick Start Guide](http://www.giantbomb.com/forums/api-developers-3017/quick-start-guide-to-using-the-api-1427959/#13)

[API documentation](http://www.giantbomb.com/api/documentation)

## Sample Requests

### Getting my API key

https://www.giantbomb.com/api/validate?link_code=B4984D&format=json

````json
{
"api_key": "9370044ca3820c6695420ea259a2c484730ff5a2"
}
````

### Testing if a User is Premium

Premium users gets back an HD link
````json
{
"error": "OK",
"limit": 1,
"offset": 0,
"number_of_page_results": 1,
"number_of_total_results": 1,
"status_code": 1,
"results": {
  "hd_url": "https:\/\/www.giantbomb.com\/api\/protected_video\/2300-8685\/?download=1"
},
"version": "1.0"
}
````

Regular accounts get back an empty array
````json
{
"error": "OK",
"limit": 1,
"offset": 0,
"number_of_page_results": 1,
"number_of_total_results": 1,
"status_code": 1,
"results": [ ],
"version": "1.0"
}
````

### list of recent videos

http://www.giantbomb.com/api/videos?api_key=9370044ca3820c6695420ea259a2c484730ff5a2&format=json

truncated response

````json
{
    "api_detail_url": "http:\/\/www.giantbomb.com\/api\/video\/2300-10064\/",
    "deck": "Remember all those guards Drew shot in the junk? They're back and looking to settle the score.",
    "hd_url": "http:\/\/www.giantbomb.com\/api\/protected_video\/2300-10064\/?download=1",
    "high_url": "http:\/\/v.giantbomb.com\/2015\/03\/30\/mc_mgs03_ep10_03252015_zlfj38ghv_1800.mp4",
    "low_url": "http:\/\/v.giantbomb.com\/2015\/03\/30\/mc_mgs03_ep10_03252015_zlfj38ghv_800.mp4",
    "id": 10064,
    "length_seconds": 4718,
    "name": "Metal Gear Scanlon 3: Part 10",
    "publish_date": "2015-03-30 21:00:00",
    "site_detail_url": "http:\/\/www.giantbomb.com\/videos\/metal-gear-scanlon-3-part-10\/2300-10064\/",
    "url": "mc_mgs03_ep10_03252015_zlfj38ghv.mp4",
    "image": {
        "icon_url": "http:\/\/static.giantbomb.com\/uploads\/square_avatar\/23\/233047\/2739067-mgs0310.jpg",
        "medium_url": "http:\/\/static.giantbomb.com\/uploads\/scale_medium\/23\/233047\/2739067-mgs0310.jpg",
        "screen_url": "http:\/\/static.giantbomb.com\/uploads\/screen_medium\/23\/233047\/2739067-mgs0310.jpg",
        "small_url": "http:\/\/static.giantbomb.com\/uploads\/scale_small\/23\/233047\/2739067-mgs0310.jpg",
        "super_url": "http:\/\/static.giantbomb.com\/uploads\/scale_large\/23\/233047\/2739067-mgs0310.jpg",
        "thumb_url": "http:\/\/static.giantbomb.com\/uploads\/scale_avatar\/23\/233047\/2739067-mgs0310.jpg",
        "tiny_url": "http:\/\/static.giantbomb.com\/uploads\/square_mini\/23\/233047\/2739067-mgs0310.jpg"
    },
    "user": "unastrike",
    "video_type": "Subscriber, Metal Gear Scanlon",
    "youtube_id": null
}
````

## Video Fields
-   api_detail_url
-   deck
-   hd_url
-   high_url
-   low_url
-   id
-   length_seconds
-   name
-   publish_date
-   site_detail_url
-   url
- image
   -    icon_url
   -    medium_url
   -    small_url
   -    super_url
   -    thumb_url
   -    tiny_url
-   user
-   video_type
-   youtube_id
