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
}