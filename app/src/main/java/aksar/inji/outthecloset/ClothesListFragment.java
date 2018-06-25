package aksar.inji.outthecloset;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import aksar.inji.outthecloset.R;

public class ClothesListFragment extends Fragment {

	private RecyclerView mClothesRecyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_clothes_list, container, false);

		mClothesRecyclerView = (RecyclerView) view
			.findViewById(R.id.clothes_recycler_view);
		mClothesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
	
		return view;
	}

	/***********************************************************************************/

	// Implementing a View Holder and an Adapter

    private class ClothesHolder extends RecyclerView.ViewHolder {
        public ClothesHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_clothes, parent, false));
        }
    }

    // Adapter

    private class ClothesAdapter extends RecyclerView.Adapter<ClothesHolder> {

        private List<Clothes> mClothes;

        public ClothesAdapter(List<Clothes> clothes) {
            mClothes = clothes;
        }

        @Override
        public ClothesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ClothesHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ClothesHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mClothes.size();
        }
    }
}