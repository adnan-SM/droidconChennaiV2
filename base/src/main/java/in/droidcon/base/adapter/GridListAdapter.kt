package `in`.droidcon.base.adapter

import `in`.droidcon.base.R
import `in`.droidcon.base.model.GridItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by Hari on 2019-08-10.
 * Adapter used for speaker and team list
 */
class GridListAdapter(
    private val listItemClickListener: ListItemClickListener
):
    ListAdapter<GridItem, GridListAdapter.GridListHolder>(SPEAKER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridListHolder {
        return GridListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_grid_item, parent, false))
    }

    override fun onBindViewHolder(holder: GridListHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class GridListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gridImgView: ImageView = itemView.findViewById(R.id.gridImgView)
        private val gridNameView: TextView = itemView.findViewById(R.id.gridTitleView)
        private val gridOrgView: TextView = itemView.findViewById(R.id.gridSubTitleView)

        fun bind(gridItemItem: GridItem, position: Int) {
            gridNameView.text = gridItemItem.gridName
            gridOrgView.text = gridItemItem.gridOrg
            itemView.setOnClickListener { listItemClickListener.onGridItemClicked(gridItemItem) }
            Glide.with(gridImgView.context)
                .load(gridItemItem.gridImg)
                .placeholder(R.drawable.ic_placeholder)
                .circleCrop()
                .error(R.drawable.ic_placeholder)
                .into(gridImgView)
        }
    }

    companion object {

        private val SPEAKER_COMPARATOR = object : DiffUtil.ItemCallback<GridItem>() {

            override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
                return oldItem.gridName == newItem.gridName
            }

            override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface ListItemClickListener {
        fun onGridItemClicked(gridItem: GridItem)
    }
}