package com.primetube;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class AdminActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> mapp = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	
	private ListView listview1;
	
	private DatabaseReference FirebaseDB = _firebase.getReference("FirebaseDB");
	private ChildEventListener _FirebaseDB_child_listener;
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		listview1 = findViewById(R.id.listview1);
		d = new AlertDialog.Builder(this);
		
		_FirebaseDB_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				FirebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(maplist);
						list.add(_childKey);
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				FirebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(maplist);
						list.add(_childKey);
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		FirebaseDB.addChildEventListener(_FirebaseDB_child_listener);
	}
	
	private void initializeLogic() {
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.ad_screen_photo, null);
			}
			
			final LinearLayout main_linear = _view.findViewById(R.id.main_linear);
			final LinearLayout linear28 = _view.findViewById(R.id.linear28);
			final LinearLayout image_linear = _view.findViewById(R.id.image_linear);
			final LinearLayout linear_delete = _view.findViewById(R.id.linear_delete);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear26 = _view.findViewById(R.id.linear26);
			final ImageView imageview_thumbnail = _view.findViewById(R.id.imageview_thumbnail);
			final TextView textview_title = _view.findViewById(R.id.textview_title);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout linear27 = _view.findViewById(R.id.linear27);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			textview_title.setText(maplist.get((int)_position).get("title").toString());
			name.setText(maplist.get((int)_position).get("name").toString());
			if (maplist.get((int)_position).get("Verification").toString().equals("true")) {
				textview2.setVisibility(View.VISIBLE);
				imageview2.setVisibility(View.VISIBLE);
			} else {
				if (maplist.get((int)_position).get("Verification").toString().equals("false")) {
					textview2.setVisibility(View.GONE);
					imageview2.setVisibility(View.GONE);
				}
			}
			if (maplist.get((int)_position).containsKey("image")) {
				imageview_thumbnail.setVisibility(View.VISIBLE);
				Glide.with(getApplicationContext()).load(Uri.parse(maplist.get((int)_position).get("image").toString())).into(imageview_thumbnail);
			} else {
				imageview_thumbnail.setVisibility(View.GONE);
			}
			imageview_thumbnail.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					d.setTitle("Veeification User Option");
					d.setMessage("Do you want to verify this user");
					d.setPositiveButton("verify this video", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							mapp = new HashMap<>();
							mapp.put("Verification", "true");
							FirebaseDB.child(list.get((int)(_position))).updateChildren(mapp);
							mapp.clear();
						}
					});
					d.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					d.create().show();
				}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}