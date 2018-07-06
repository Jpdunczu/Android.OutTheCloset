package aksar.inji.outthecloset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class ClothesListFragment extends Fragment {

    private static final String DIALOG_DELETE = "DeleteDialog";
    private static final int CONFIRM_DELETE = 0;

	private RecyclerView mClothesRecyclerView;
	private ClothesAdapter mAdapter;

	private UUID checkedClothes;

	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(true);
    }

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
        implements View.OnClickListener, View.OnLongClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCostTextView;
        private CheckBox mCheckBox;

        /****************************/
        // for BindClothes
        private Clothes mClothes;

        public ClothesHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_clothes, parent, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.clothes_name);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_added);
            mCostTextView = (TextView) itemView.findViewById(R.id.clothes_price);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.select_item);

            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedClothes = mClothes.getmId();
                }
            });
        }

        public void bind(Clothes clothing) {
            mClothes = clothing;
            mTitleTextView.setText(mClothes.getmName());
            mDateTextView.setText(mClothes.getmDate().toString());
            mCostTextView.setText(mClothes.getmCost());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    "Hold to delete...", Toast.LENGTH_SHORT).show();
            if (mCheckBox.getVisibility() == View.VISIBLE) {
                mCheckBox.setVisibility(View.GONE);
                return;
            }
            Intent intent = ClothesPagerActivity.newIntent(getActivity(), mClothes.getmId());

            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
                mCheckBox.setVisibility(View.VISIBLE);
                return true;
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

        // added position variable for future challenge to re-load only the one item which was edited, not the whole list in UpdateUi();
        // mAdapter.notifyItemChanged(position);
        //
        @Override
        public void onBindViewHolder(ClothesHolder holder, int position) {
            Clothes clothing = mClothes.get(position);
            holder.bind(clothing);
        }

        @Override
        public int getItemCount() {
            return mClothes.size();
        }

        public void setmClothes(List<Clothes> clothes) {
            mClothes = clothes;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ClothesLab clothesLab = ClothesLab.get(getActivity());
        UUID brandId = (UUID) getActivity().getIntent().getSerializableExtra(ClothesListActivity.EXTRA_BRAND_ID);
        List<Clothes> clothes = clothesLab.getClothesByBrand(brandId);

        if(mAdapter == null) {
            mAdapter = new ClothesAdapter(clothes);
            mClothesRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setmClothes(clothes);
            mAdapter.notifyDataSetChanged();
            //mAdapter.notifyItemChanged();
        }
    }

    /*
     *
     * OPTIONS MENU
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_clothes_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_clothes:
                Intent intent = new Intent(getActivity(), ClothesActivity.class);
                startActivity(intent);
                return true;
            case R.id.delete_old_clothes:
                if ( checkedClothes == null ) {

                } else {
                    FragmentManager fragmentManager = getFragmentManager();
                    ConfirmDeleteFragment confirm = new ConfirmDeleteFragment();
                    confirm.setTargetFragment(ClothesListFragment.this, CONFIRM_DELETE);
                    confirm.show(fragmentManager, DIALOG_DELETE);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    *
    * Getting the result back from the DialogFragment for the Delete button.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == CONFIRM_DELETE) {
            ClothesLab.get(getActivity()).deleteClothes(checkedClothes);
            //mCheckBox.setVisibility(View.GONE);
            updateUI();
        }
    }
}