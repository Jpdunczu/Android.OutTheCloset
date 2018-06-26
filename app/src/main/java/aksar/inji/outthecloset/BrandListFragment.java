package aksar.inji.outthecloset;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BrandListFragment extends Fragment {
    private RecyclerView mBrandRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand_list, container, false);

        mBrandRecyclerView = (RecyclerView) view.findViewById(R.id.brand_recycler_view);
        mBrandRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
