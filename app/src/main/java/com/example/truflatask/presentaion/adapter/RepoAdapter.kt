package  com.example.truflatask.presentaion.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.truflatask.R
import com.example.truflatask.database.Repo
import com.example.truflatask.network.NetworkState
import kotlinx.android.synthetic.main.item_network_state.view.*
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter(private val context: Context) : PagedListAdapter<Repo, RecyclerView.ViewHolder>(
        DIFF_CALLBACK
) {

    private var networkState: NetworkState.Status? = null

    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1


    companion object {

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Repo> =
                object : DiffUtil.ItemCallback<Repo>() {
                    override fun areItemsTheSame(listBean: Repo, t1: Repo): Boolean {
                        return listBean.id == t1.id
                    }

                    @SuppressLint("DiffUtilEquals")
                    override fun areContentsTheSame(listBean: Repo, t1: Repo): Boolean {
                        return listBean == t1
                    }
                }


    } // CMO


    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.Status.SUCCESS
    } // fun hasExtraRow

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else
            TYPE_ITEM

    } // fun getItemViewType

    fun setNetworkState(newNetworkState: NetworkState.Status) {
        val previousState = networkState
        val previousExtraRow = hasExtraRow()
        networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    } // fun setNetworkState


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_PROGRESS) {
            val view: View = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_network_state,
                    parent,
                    false
            )
            NetworkStateItemViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_repo,
                    parent,
                    false
            )
            ViewHolder(view)
        }
    } // fun of onCreateViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is ViewHolder) {
            val photo: Repo? = getItem(position)
            holder.bind(
                    photo!!,
                    holder,
                    context = context)

        } else if (holder is NetworkStateItemViewHolder) {
            holder.bind(networkState!!, holder, context = context)
        }

    } // fun of onBindViewHolder


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val imageView: ImageView = itemView.img
        private val txtName: TextView = itemView.txt_name
        private val txtDescription: TextView = itemView.txt_description
        private val txtWatchers: TextView = itemView.txt_watchers


        fun bind(
                repo: Repo,
                holder: ViewHolder,
                context: Context
        ) {

            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions().fitCenter().placeholder(R.color.purple_200))
                    .load(repo.url)
                    .into(holder.imageView)

            holder.txtName.text = repo.name
            holder.txtDescription.text = repo.description
            holder.txtWatchers.text = "${repo.watchers}"

        }


    } // class of ViewHolder

    class NetworkStateItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressBar: ProgressBar = itemView.progress_bar
        private val error_msg: TextView = itemView.error_msg

        fun bind(
                networkState: NetworkState.Status,
                holder: NetworkStateItemViewHolder,
                context: Context
        ) {

            if (networkState == NetworkState.Status.RUNNING) {
                holder.progressBar.visibility = View.VISIBLE
            } else {
                holder.progressBar.visibility = View.GONE
            }

            if (networkState == NetworkState.Status.FAILED) {
                holder.error_msg.visibility = View.VISIBLE
                holder.error_msg.text = context.getString(R.string.something_wrong)
            } else {
                holder.error_msg.visibility = View.GONE
            }
        }

    } // class of NetworkStateItemViewHolder

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  // class of ViewHolder


} // class of RepoAdapter