    package com.example.pmdmrrbtarea2v2;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;

    import androidx.recyclerview.widget.RecyclerView;

    import com.example.pmdmrrbtarea2v2.databinding.ItemCardviewBinding;

    import java.util.List;

    public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

        public final List<GameData> gameList;
        private GameAdapter.OnItemClickListener listener;

        // Constructor que recibe el listener
        public GameAdapter(List<GameData> gameList,  GameAdapter.OnItemClickListener listener) {
            this.gameList = gameList;
            this.listener=listener;
        }

        public interface OnItemClickListener {


            void onItemClick(int position);
        }

        @Override
        public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflar el layout de cada item del RecyclerView

            ItemCardviewBinding binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new GameViewHolder(binding, listener);
        }

        @Override
        public void onBindViewHolder(GameViewHolder holder, int position) {
            GameData gameData = gameList.get(position);
            holder.bind(gameData);

        }

        @Override
        public int getItemCount() {
            return gameList.size();
        }

    }