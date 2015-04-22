# Giantbomb API

## Forum Links

[Quick Start Guide](http://www.giantbomb.com/forums/api-developers-3017/quick-start-guide-to-using-the-api-1427959/#13)

[API documentation](http://www.giantbomb.com/api/documentation)

## Sample Requests

[list of recent videos](http://www.giantbomb.com/api/videos?api_key=9370044ca3820c6695420ea259a2c484730ff5a2&format=json)

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