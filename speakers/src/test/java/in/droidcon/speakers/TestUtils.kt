package `in`.droidcon.speakers

import `in`.droidcon.speakers.remote.SpeakerModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

val speakersJson = """
[
  {
    "id": "00000000-0000-0000-0000-000000000004",
    "firstName": "Aiden",
    "lastName": "Test",
    "fullName": "Aiden Test",
    "bio": "Pop culture fanatic. Friend of animals everywhere. Student. Thinker. Bacon practitioner.",
    "tagLine": "Professional public speaker",
    "profilePicture": "https://sessionize.com/image?f=e82a4a80c16abc546f05e05a8ef591ef,400,400,True,False,test4.jpg",
    "sessions": [
      {
        "id": 14022,
        "name": "Aiden's Session"
      }
    ],
    "isTopSpeaker": true,
    "links": [
      {
        "title": "Twitter",
        "url": "https://twitter.com/sessionizecom",
        "linkType": "Twitter"
      },
      {
        "title": "LinkedIn",
        "url": "http://linkedin.com/in/domagojpa",
        "linkType": "LinkedIn"
      }
    ],
    "questionAnswers": [],
    "categories": []
  },
  {
    "id": "00000000-0000-0000-0000-000000000008",
    "firstName": "Ava",
    "lastName": "Test",
    "fullName": "Ava Test",
    "bio": "Student. Wannabe creator. Incurable music advocate.",
    "tagLine": "PR specialist",
    "profilePicture": "https://sessionize.com/image?f=4e1b91f4d9523cabcbce0759bb16d61a,400,400,True,False,test8.jpg",
    "sessions": [
      {
        "id": 14026,
        "name": "Ava's Session"
      }
    ],
    "isTopSpeaker": false,
    "links": [],
    "questionAnswers": [],
    "categories": []
  },
  {
    "id": "00000000-0000-0000-0000-000000000002",
    "firstName": "Emma",
    "lastName": "Test",
    "fullName": "Emma Test",
    "bio": "General student. Avid coffee specialist. Web aficionado. Food geek. Bacon expert. Alcohol junkie.",
    "tagLine": "Miss Venezuela",
    "profilePicture": "https://sessionize.com/image?f=33cce42e13b2f74e43ce24812cd6b863,400,400,True,False,test2.jpg",
    "sessions": [
      {
        "id": 14020,
        "name": "Emma's Session"
      }
    ],
    "isTopSpeaker": true,
    "links": [],
    "questionAnswers": [],
    "categories": []
  },
  {
    "id": "00000000-0000-0000-0000-000000000001",
    "firstName": "Jackson",
    "lastName": "Test",
    "fullName": "Jackson Test",
    "bio": "Thinker. Food trailblazer. Organizer. Student. Pop culture fanatic. Writer. Passionate twitter lover. Music advocate.",
    "tagLine": "Tech guy",
    "profilePicture": "https://sessionize.com/image?f=c60d0ef66e02ee405bbe89ef29b7f626,400,400,True,False,test1.jpg",
    "sessions": [
      {
        "id": 14019,
        "name": "Jackson's Session"
      }
    ],
    "isTopSpeaker": true,
    "links": [
      {
        "title": "Twitter",
        "url": "https://twitter.com/sessionizecom",
        "linkType": "Twitter"
      }
    ],
    "questionAnswers": [],
    "categories": []
  }
]
        """

fun getSpeakerModelList(): List<SpeakerModel> {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))

    return json.parse(SpeakerModel.serializer().list, speakersJson)
}