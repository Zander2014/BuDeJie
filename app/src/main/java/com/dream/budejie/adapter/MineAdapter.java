package com.dream.budejie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.dream.budejie.R;
import com.dream.budejie.entity.MineBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MineAdapter extends BaseAdapter {

    private ImageOptions build;
    private Context context;
    List<MineBean> list;

    public MineAdapter(Context context, List<MineBean> list) {
        this.context = context;
        this.list = list;
        build = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(90))
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setConfig(Bitmap.Config.ARGB_8888)
                .build();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mine, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MineBean bean = list.get(position);
        x.image().bind(viewHolder.pic, bean.getPic().toString(), build);
        viewHolder.title.setText(bean.getTitle());
        return convertView;
    }

    class ViewHolder {
        private ImageView pic;
        private TextView title;
    }

}
