package `in`.droidcon.speakers.remote

import `in`.droidcon.base.repo.remote.EntityMapper
import `in`.droidcon.speakers.model.SpeakerEntity

object SpeakerMapper : EntityMapper<SpeakerModel, SpeakerEntity> {
    override fun mapFromRemote(type: SpeakerModel): SpeakerEntity {
        return SpeakerEntity(
            speakerId = type.id,
            speakerName = type.fullName,

            /*Sessionize doesn't provide company name
             *However, it has an optional field for Company Website
             *So, if the Speaker filled Company website, it'll show Company website in `speakerOrg`
             *otherwise it'll show "N/A"
             *For Debug, it'll always show "N/A", as the debug API doesn't provide Company Website*/
            speakerOrg = type.links.find {
                it.linkType.equals("Company_Website", true)
            }?.url ?: "N/A",

            speakerBlurbs = type.bio,
            speakerImg = type.profilePicture,
            talkId = type.sessions.map {
                it.id.toString()
            }
        )
    }
}