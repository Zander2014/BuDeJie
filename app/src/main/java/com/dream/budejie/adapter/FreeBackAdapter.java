package com.dream.budejie.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dream.budejie.R;
import com.dream.budejie.entity.FreeBean;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/12/27.
 */
public class FreeBackAdapter extends BaseAdapter {

    private Context context;
    private List<FreeBean> list;

    public FreeBackAdapter(Context context, List<FreeBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.freeback_item, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.freeBack_item_text);
            holder.time = (TextView) convertView.findViewById(R.id.freeBack_item_time);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        FreeBean freeBean = list.get(position);
        holder.text.setText(freeBean.getText());
        holder.time.setText(freeBean.getTime());
        return convertView;
    }
    class ViewHolder {
        TextView time, text;
    }

    /**
     * 添加数据
     *
     * @param collection
     */
    public void add(Collection<? extends FreeBean> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * 清空Adapter
     */
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}
