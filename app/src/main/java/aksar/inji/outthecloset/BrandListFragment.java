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

public class BrandListFragment extends Fragment {

    private RecyclerView mBrandRecyclerView;
    private BrandAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand_list, container, false);

        mBrandRecyclerView = (RecyclerView) view.findViewById(R.id.brand_recycler_view);
        mBrandRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        BrandLab brandLab = BrandLab.get(getActivity());
        List<Brands> brands = brandLab.getmBrands();

        mAdapter = new BrandAdapter(brands);
        mBrandRecyclerView.setAdapter(mAdapter);
    }

    private class BrandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBrandNameTV;
        private TextView mBrandWorthTV;
        private TextView mBrandItemCount;

        private Brands mBrand;


        public BrandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_brand, parent, false));
            itemView.setOnClickListener(this);

            mBrandNameTV = (TextView) itemView.findViewById(R.id.brand_name);
            mBrandWorthTV = (TextView) itemView.findViewById(R.id.brand_cost);
            mBrandItemCount = (TextView) itemView.findViewById(R.id.item_count);
        }

        public void bind(Brands brands) {
            mBrand = brands;
            mBrandNameTV.setText(mBrand.getmBrandName());
            mBrandWorthTV.setText(mBrand.getmBrandWorth());
            mBrandItemCount.setText(mBrand.getmBrandCount());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ClothesListActivity.newIntent(getActivity(), mBrand.getmId());
            startActivity(intent);
        }
    }

    private class BrandAdapter extends RecyclerView.Adapter<BrandHolder> {

        private List<Brands> mBrands;

        public BrandAdapter(List<Brands> brands) {
            mBrands = brands;
        }

        @Override
        public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new BrandHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BrandHolder holder, int position) {
            Brands brands = mBrands.get(position);
            holder.bind(brands);
        }

        @Override
        public int getItemCount() {
            return mBrands.size();
        }
    }
}
