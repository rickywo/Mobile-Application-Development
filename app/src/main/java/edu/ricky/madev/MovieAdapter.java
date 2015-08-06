package edu.ricky.madev;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
//import android.view.LayoutInflater;
import android.widget.SimpleAdapter;

public class MovieAdapter extends SimpleAdapter {
//	private Context mContext;
//	public LayoutInflater inflater = null;

	public MovieAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
//		mContext = context;
//		inflater = (LayoutInflater) mContext
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//View vi = convertView;
		View vi = super.getView(position, convertView, parent);
		if (convertView == null)
			vi = inflater.inflate(R.layout.listview_layout, null);

		HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);

		new DownloadTask((ImageView) vi.findViewById(R.id.iv_mvPoster))
				.execute((String) data.get("uri"));

		return vi;
	}*/

}
