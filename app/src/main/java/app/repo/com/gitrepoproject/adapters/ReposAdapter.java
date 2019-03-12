package app.repo.com.gitrepoproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.repo.com.gitrepoproject.DeteilsActivity;
import app.repo.com.gitrepoproject.R;
import app.repo.com.gitrepoproject.model.Repo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    List<Repo> repoList = new ArrayList<>();

    public ReposAdapter(List<Repo> repos) {
        this.repoList = repos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Repo repo = repoList.get(position);

        holder.nameTextView.setText(repo.getName());
        holder.starsTextView.setText(String.valueOf(repo.getStargazersCount()));
        holder.bookmarkImageView.setVisibility(repo.isBookmarked() ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DeteilsActivity.class);
                intent.putExtra(DeteilsActivity.REPO, repo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_text)
        TextView nameTextView;
        @BindView(R.id.stars_text)
        TextView starsTextView;
        @BindView(R.id.bookmark_image)
        ImageView bookmarkImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}