package com.primetube;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class LoginActivity extends AppCompatActivity {
	
	public final int REQ_CD_FPROF = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String fpath = "";
	private String fname = "";
	private HashMap<String, Object> Map = new HashMap<>();
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private TextView textview1_login_up;
	private TextView textview2_create_up;
	private LinearLayout linear3x;
	private LinearLayout login_box;
	private LinearLayout create_box;
	private LinearLayout prof_box;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private EditText edittext1_log_email;
	private EditText edittext2_log_pass;
	private Button button1;
	private LinearLayout linear13;
	private LinearLayout linear12;
	private LinearLayout linear14;
	private EditText edittext3_reg_email;
	private EditText edittext4_reg_pass;
	private Button button2;
	private LinearLayout linear11;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private EditText edittext5_name;
	private TextView textview2;
	private ImageView imageview1;
	private Button button3;
	
	private Intent i = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private Intent ho = new Intent();
	private SharedPreferences profile;
	private SharedPreferences name;
	private Intent fprof = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference fbsgprof = _firebase_storage.getReference("fbsgprof");
	private OnCompleteListener<Uri> _fbsgprof_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbsgprof_download_success_listener;
	private OnSuccessListener _fbsgprof_delete_success_listener;
	private OnProgressListener _fbsgprof_upload_progress_listener;
	private OnProgressListener _fbsgprof_download_progress_listener;
	private OnFailureListener _fbsgprof_failure_listener;
	
	private Intent qq = new Intent();
	private SharedPreferences uu;
	private SharedPreferences transicph;
	private DatabaseReference User = _firebase.getReference("User");
	private ChildEventListener _User_child_listener;
	private SharedPreferences nampro;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = findViewById(R.id.linear2);
		linear1 = findViewById(R.id.linear1);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		textview1_login_up = findViewById(R.id.textview1_login_up);
		textview2_create_up = findViewById(R.id.textview2_create_up);
		linear3x = findViewById(R.id.linear3x);
		login_box = findViewById(R.id.login_box);
		create_box = findViewById(R.id.create_box);
		prof_box = findViewById(R.id.prof_box);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear8 = findViewById(R.id.linear8);
		edittext1_log_email = findViewById(R.id.edittext1_log_email);
		edittext2_log_pass = findViewById(R.id.edittext2_log_pass);
		button1 = findViewById(R.id.button1);
		linear13 = findViewById(R.id.linear13);
		linear12 = findViewById(R.id.linear12);
		linear14 = findViewById(R.id.linear14);
		edittext3_reg_email = findViewById(R.id.edittext3_reg_email);
		edittext4_reg_pass = findViewById(R.id.edittext4_reg_pass);
		button2 = findViewById(R.id.button2);
		linear11 = findViewById(R.id.linear11);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		edittext5_name = findViewById(R.id.edittext5_name);
		textview2 = findViewById(R.id.textview2);
		imageview1 = findViewById(R.id.imageview1);
		button3 = findViewById(R.id.button3);
		auth = FirebaseAuth.getInstance();
		profile = getSharedPreferences("profile", Activity.MODE_PRIVATE);
		name = getSharedPreferences("name", Activity.MODE_PRIVATE);
		fprof.setType("image/*");
		fprof.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		uu = getSharedPreferences("uu", Activity.MODE_PRIVATE);
		transicph = getSharedPreferences("transicph", Activity.MODE_PRIVATE);
		nampro = getSharedPreferences("nampro", Activity.MODE_PRIVATE);
		
		textview1_login_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				create_box.setVisibility(View.GONE);
				prof_box.setVisibility(View.GONE);
				login_box.setVisibility(View.VISIBLE);
				textview1_login_up.setTextColor(0xFFF44336);
				textview2_create_up.setTextColor(0xFF000000);
			}
		});
		
		textview2_create_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login_box.setVisibility(View.GONE);
				prof_box.setVisibility(View.GONE);
				create_box.setVisibility(View.VISIBLE);
				textview2_create_up.setTextColor(0xFFF44336);
				textview1_login_up.setTextColor(0xFF000000);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1_log_email.getText().toString().equals("") || edittext2_log_pass.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "please fill this fields");
				} else {
					auth.signInWithEmailAndPassword(edittext1_log_email.getText().toString(), edittext2_log_pass.getText().toString()).addOnCompleteListener(LoginActivity.this, _auth_sign_in_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "Please wait...");
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext3_reg_email.getText().toString().equals("") || edittext4_reg_pass.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "fill this fields");
				} else {
					auth.createUserWithEmailAndPassword(edittext3_reg_email.getText().toString(), edittext4_reg_pass.getText().toString()).addOnCompleteListener(LoginActivity.this, _auth_create_user_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "creating a new account please wait");
				}
			}
		});
		
		edittext5_name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 30) {
					edittext5_name.setTextColor(0xFFF44336);
					SketchwareUtil.showMessage(getApplicationContext(), "error , the maximum number of letters is 30 only !");
					button3.setEnabled(false);
				} else {
					edittext5_name.setTextColor(0xFF000000);
					button3.setEnabled(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fprof, REQ_CD_FPROF);
				button3.setEnabled(false);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext5_name.getText().toString().length() > 30) {
					SketchwareUtil.showMessage(getApplicationContext(), "The maximum number of letters in your name is 30");
				} else {
					if (!edittext5_name.getText().toString().equals("") && !transicph.getString("profile", "").equals("")) {
						nampro.edit().putString("name", edittext5_name.getText().toString()).commit();
						nampro.edit().putString("profile", transicph.getString("profile", "")).commit();
						ho.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(ho);
						transicph.edit().remove("profile").commit();
						SketchwareUtil.showMessage(getApplicationContext(), "successfully logged in");
						finish();
					} else {
						if (profile.getString("profile", "").equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "please add profile picture");
						} else {
							if (edittext5_name.getText().toString().equals("")) {
								SketchwareUtil.showMessage(getApplicationContext(), "please enter your name");
							}
						}
					}
				}
			}
		});
		
		_fbsgprof_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), "progress : ".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
		};
		
		_fbsgprof_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_fbsgprof_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				nampro.edit().putString("profile", _downloadUrl).commit();
				SketchwareUtil.showMessage(getApplicationContext(), "successfully uploaded your photo");
				button3.setEnabled(true);
			}
		};
		
		_fbsgprof_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_fbsgprof_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_fbsgprof_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), "failed to upload photo , the cause is : ".concat(_message));
			}
		};
		
		_User_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		User.addChildEventListener(_User_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					Map = new HashMap<>();
					Map.put("Email", edittext3_reg_email.getText().toString());
					Map.put("Password", edittext4_reg_pass.getText().toString());
					Map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					Map.put("Admin", "false");
					User.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(Map);
					login_box.setVisibility(View.GONE);
					create_box.setVisibility(View.GONE);
					prof_box.setVisibility(View.VISIBLE);
					textview2_create_up.setTextColor(0xFF000000);
					textview1_login_up.setTextColor(0xFFF44336);
					SketchwareUtil.showMessage(getApplicationContext(), "successfully created a new account now Login.");
					edittext1_log_email.setText(uu.getString("email", ""));
					edittext2_log_pass.setText(uu.getString("password", ""));
					Map.put("Email", edittext1_log_email.getText().toString());
					Map.put("Password", edittext2_log_pass.getText().toString());
				} else {
					SketchwareUtil.showMessage(getApplicationContext(), "failed to create a new account please try again , the cause is : ".concat(_errorMessage));
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					i.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(i);
					finish();
				} else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		create_box.setVisibility(View.GONE);
		prof_box.setVisibility(View.GONE);
		login_box.setVisibility(View.VISIBLE);
		textview1_login_up.setTextColor(0xFFF44336);
		textview2_create_up.setTextColor(0xFF000000);
		_radius_to(linear4, 15, 15, "#FFFFFF");
		_radius_to(linear5, 15, 15, "#FFFFFF");
		_radius_to(linear13, 15, 15, "#FFFFFF");
		_radius_to(linear12, 15, 15, "#FFFFFF");
		_radius_to(linear11, 15, 15, "#FFFFFF");
		_radius_to(button1, 40, 15, "#3F51B5");
		_radius_to(button2, 40, 15, "#3F51B5");
		_radius_to(button3, 40, 15, "#3F51B5");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FPROF:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				fpath = _filePath.get((int)(0));
				fname = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(fpath, 1024, 1024));
				fbsgprof.child(fname).putFile(Uri.fromFile(new File(fpath))).addOnFailureListener(_fbsgprof_failure_listener).addOnProgressListener(_fbsgprof_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return fbsgprof.child(fname).getDownloadUrl();
					}}).addOnCompleteListener(_fbsgprof_upload_success_listener);
				SketchwareUtil.showMessage(getApplicationContext(), "uploading your profile picture");
			}
			else {
				button3.setEnabled(true);
				SketchwareUtil.showMessage(getApplicationContext(), "upload cancelled");
			}
			break;
			default:
			break;
		}
	}
	
	public void _radius_to(final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
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