package com.qinaqian.game.baby_login.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dream.budejie.R;

import java.util.List;
import java.util.Map;

/**
 * Created by kuku on 2015/12/23.
 */
public class InstallAdapter extends SimpleAdapter {
    /**
     * 布局加载�?
     */
    private LayoutInflater mInflater;
    private List<Map<String, Object>> data;
    private Context context;

    public InstallAdapter(Context context, List<Map<String, Object>> data,
                          int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    public int getCount() {
        return data.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.install, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_app_ico.setBackgroundDrawable((Drawable) data.get(position)
                .get("appico"));
        holder.tv_app_name.setText((String) data.get(position).get("appname"));
        holder.tv_package
                .setText((String) data.get(position).get("apppackage"));
        return convertView;
    }

    public class ViewHolder {
        /**
         * 应用图标
         */
        public ImageView iv_app_ico;
        /**
         * 应用名字
         */
        public TextView tv_app_name;
        /**
         * 卸载按钮
         */
        public Button btn_update;
        /**
         * 应用包名 默认隐藏 用于传输数据
         */
        public TextView tv_package;

        public ViewHolder(View convertView) {
            iv_app_ico = (ImageView) convertView.findViewById(R.id.iv_app_ico);
            tv_app_name = (TextView) convertView.findViewById(R.id.tv_app_name);
            tv_package = (TextView) convertView.findViewById(R.id.tv_package);
            btn_update = (Button) convertView.findViewById(R.id.btn_update);
            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
//					Contants.downname = tv_app_name.getText().toString();
                    //启卸载应用的界面
                    Uri uri = Uri.parse("package:" + tv_package.getText());
                    context.startActivity(new Intent(Intent.ACTION_DELETE, uri));

                }
            });
        }

    }
}
