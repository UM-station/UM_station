package com.example.umstation;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter{
    private ArrayList<MyCoupon> mCoupons = new ArrayList<>();

    @Override
    public int getCount() {
        return mCoupons.size();
    }

    @Override
    public Object getItem(int position) {
        return mCoupons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }
        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyCoupon mycoupon = (MyCoupon) getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        iv_img.setImageDrawable(mycoupon.getIcon());
        tv_name.setText(mycoupon.getName());
        tv_contents.setText(mycoupon.getContents());
        return convertView;
    }

    public void addCoupon(Drawable img, String name, String contents) {

        MyCoupon mItem = new MyCoupon();

        /* MyItem에 아이템을 setting한다. */
        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setContents(contents);

        /* mItems에 MyItem을 추가한다. */
        mCoupons.add(mItem);

    }
}

