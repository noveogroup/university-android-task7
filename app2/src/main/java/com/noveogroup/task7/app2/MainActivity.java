package com.noveogroup.task7.app2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.noveogroup.task7.app2.db.OpenHelper;


public class MainActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ShowListFragment list;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.insert_fragment, new InsertFragment())
				.replace(R.id.list_fragment, list = new ShowListFragment())
				.commit();
		getSupportFragmentManager().executePendingTransactions();
		getSupportLoaderManager().initLoader(0, null, list);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		OpenHelper.getInstance(this).close();
	}
}
