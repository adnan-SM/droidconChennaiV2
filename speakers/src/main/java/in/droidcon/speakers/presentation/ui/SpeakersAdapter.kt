package `in`.droidcon.speakers.presentation.ui

import `in`.droidcon.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SpeakersAdapter(
    private val listItemClickListener: ListItemClickListener):
    ListAdapter<SpeakerEntity, SpeakersAdapter.SpeakersHolder>(SPEAKER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakersHolder {
        return SpeakersHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.view_speaker_item, parent, false))
    }

    override fun onBindViewHolder(holder: SpeakersHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class SpeakersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val speakerImgView: ImageView = itemView.findViewById(R.id.speakerImgView)
        private val speakerNameView: TextView = itemView.findViewById(R.id.speakerNameView)
        private val speakerOrgView: TextView = itemView.findViewById(R.id.speakerOrgView)

        fun bind(speakerItem: SpeakerEntity, position: Int) {
            speakerNameView.text = speakerItem.speakerName
            speakerOrgView.text = speakerItem.speakerOrg
            itemView.setOnClickListener { listItemClickListener.onSpeakerItemClicked(speakerItem) }
            Glide.with(speakerImgView.context)
                .load(speakerItem.speakerImg)
                .placeholder(R.drawable.ic_placeholder)
                .circleCrop()
                .error(R.drawable.ic_placeholder)
                .into(speakerImgView)
        }
    }

    companion object {

        private val SPEAKER_COMPARATOR = object : DiffUtil.ItemCallback<SpeakerEntity>() {

            override fun areItemsTheSame(oldItem: SpeakerEntity, newItem: SpeakerEntity): Boolean {
                return oldItem.speakerName == newItem.speakerName
            }

            override fun areContentsTheSame(oldItem: SpeakerEntity, newItem: SpeakerEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface ListItemClickListener {
        fun onSpeakerItemClicked(speakerItem: SpeakerEntity)
    }
}