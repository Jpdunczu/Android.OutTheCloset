public abstract class SingleFragmentActivity extends AppCompatActivity {
	
	protected abstract Fragment createFragment();
	// sub classes of SingleFragmentActivity will implement this method to return an instance of the fragment that the activity is hosting.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction()
				.add(R.id.fragment_container, fragment)
				.commit();
		}
	}
}