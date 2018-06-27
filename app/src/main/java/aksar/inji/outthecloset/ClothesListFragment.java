package aksar.inji.outthecloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aksar.inji.outthecloset.R;

public class ClothesListFragment extends Fragment {

	private RecyclerView mClothesRecyclerView;
	private ClothesAdapter mAdapter;

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

    private class ClothesHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCostTextView;

        /****************************/
        // for BindClothes
        private Clothes mClothes;

        public ClothesHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_clothes, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.clothes_name);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_added);
            mCostTextView = (TextView) itemView.findViewById(R.id.clothes_price);
        }

        public void bind(Clothes clothing) {
            mClothes = clothing;
            mTitleTextView.setText(mClothes.getmName());
            mDateTextView.setText(mClothes.getmDate().toString());
            mCostTextView.setText(mClothes.getmCost());
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(getActivity(), ClothesActivity.class);
            //Intent intent = ClothesActivity.newIntent(getActivity(), mClothes.getmId(), mClothes.getmBrandId());
            Intent intent = ClothesActivity.newIntent(getActivity(), mClothes.getmId());
            startActivity(intent);
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
            Clothes clothing = mClothes.get(position);
            holder.bind(clothing);
        }

        @Override
        public int getItemCount() {
            return mClothes.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ClothesLab clothesLab = ClothesLab.get(getActivity());
        List<Clothes> clothes = clothesLab.getClothes();

        if(mAdapter == null) {
            mAdapter = new ClothesAdapter(clothes);
            mClothesRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}