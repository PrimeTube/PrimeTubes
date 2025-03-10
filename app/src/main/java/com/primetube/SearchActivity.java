package com.primetube;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class SearchActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ss = "";
	private double n = 0;
	private String save = "";
	private double number = 0;
	private String value = "";
	private double length = 0;
	private double n1 = 0;
	private HashMap<String, Object> cacheMap = new HashMap<>();
	private double len = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> itemsList = new ArrayList<>();
	
	private LinearLayout actionbar_linear;
	private ListView listview1;
	private ListView listview2;
	private EditText edittext2;
	private ImageView imageview6;
	
	private DatabaseReference FirebaseDB = _firebase.getReference("FirebaseDB");
	private ChildEventListener _FirebaseDB_child_listener;
	private Intent intent = new Intent();
	private RequestNetwork internet;
	private RequestNetwork.RequestListener _internet_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.search);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		actionbar_linear = findViewById(R.id.actionbar_linear);
		listview1 = findViewById(R.id.listview1);
		listview2 = findViewById(R.id.listview2);
		edittext2 = findViewById(R.id.edittext2);
		imageview6 = findViewById(R.id.imageview6);
		internet = new RequestNetwork(this);
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
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
						if (_charSeq.length() > 0) {
							n = maplist.size() - 1;
							len = maplist.size();
							for(int _repeat132 = 0; _repeat132 < (int)(len); _repeat132++) {
								if (maplist.get((int)n).get("app_name").toString().toLowerCase().contains(_charSeq.toLowerCase())) {
									
								} else {
									maplist.remove((int)(n));
								}
								n--;
							}
						}
						listview2.setAdapter(new Listview2Adapter(maplist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
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
						listview2.setAdapter(new Listview2Adapter(maplist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
						listview2.setAdapter(new Listview2Adapter(maplist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
						listview2.setAdapter(new Listview2Adapter(maplist));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		FirebaseDB.addChildEventListener(_FirebaseDB_child_listener);
		
		_internet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), "No Internet Connection...⚠️");
			}
		};
	}
	
	private void initializeLogic() {
		_card_style(actionbar_linear, 20, 25, "#FFFFFF");
		internet.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _internet_request_listener);
	}
	
	public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _searchInListmap(final String _value, final ArrayList<HashMap<String, Object>> _listmap, final String _key) {
		_listmap.clear();
		n1 = 0;
		for(int _repeat13 = 0; _repeat13 < (int)(itemsList.size()); _repeat13++) {
			if (itemsList.get((int)n1).get(_key).toString().toLowerCase().contains(_value)) {
				cacheMap = itemsList.get((int)n1);
				_listmap.add(cacheMap);
			}
			n1++;
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
					intent.setClass(getApplicationContext(), VideoShortActivity.class);
					intent.putExtra("video", maplist.get((int)_position).get("video").toString());
					intent.putExtra("title", maplist.get((int)_position).get("title").toString());
					intent.putExtra("description", maplist.get((int)_position).get("description").toString());
					intent.putExtra("name", maplist.get((int)_position).get("name").toString());
					intent.putExtra("time", maplist.get((int)_position).get("time").toString());
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
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