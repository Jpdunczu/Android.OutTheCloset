package aksar.inji.outthecloset;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BrandListFragment extends Fragment {

    private static final String OOPS_NO_CLOTHES = "NoClothesInTheCloset";

    private RecyclerView mBrandRecyclerView;
    private BrandAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand_list, container, false);

        mBrandRecyclerView = (RecyclerView) view.findViewById(R.id.brand_recycler_view);
        mBrandRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        BrandLab brandLab = BrandLab.get(getActivity());
        List<Brands> brands = brandLab.getmBrands();
        if (brands.isEmpty()) {
            FragmentManager fragmentManager = getFragmentManager();
            NoBrandsFragment dialog = new NoBrandsFragment();
            dialog.show(fragmentManager, OOPS_NO_CLOTHES);
        }

        if (mAdapter == null) {
            mAdapter = new BrandAdapter(brands);
            mBrandRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setBrands(brands);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BrandHolder extends ViewHolder implements View.OnClickListener {

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

        public void setBrands(List<Brands> brands) {
            mBrands = brands;
        }
    }

    /*
     *
     * OPTIONS MENU
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.brands_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_brand:
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
