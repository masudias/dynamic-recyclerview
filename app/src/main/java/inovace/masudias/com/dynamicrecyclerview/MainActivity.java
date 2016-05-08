package inovace.masudias.com.dynamicrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListObject> firstList = new ArrayList<ListObject>();
    private ArrayList<ListObject> secondList = new ArrayList<ListObject>();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private DynamicListAdapter mDynamicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_list);

        firstList = Utilities.populateFirstList();
        secondList = Utilities.populateSecondList();

        // Initialize the list
        mDynamicListAdapter = new DynamicListAdapter();
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDynamicListAdapter);
    }

    private class DynamicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int FOOTER_VIEW = 1;
        private static final int FIRST_LIST_ITEM_VIEW = 2;
        private static final int FIRST_LIST_HEADER_VIEW = 3;
        private static final int SECOND_LIST_ITEM_VIEW = 4;
        private static final int SECOND_LIST_HEADER_VIEW = 5;

        public DynamicListAdapter() {
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            // List items of first list
            private TextView mTextDescription1;
            private TextView mListItemTitle1;

            // List items of second list
            private TextView mTextDescription2;
            private TextView mListItemTitle2;

            // Element of footer view
            private TextView footerTextView;

            public ViewHolder(final View itemView) {
                super(itemView);

                // Get the view of the elements of first list
                mTextDescription1 = (TextView) itemView.findViewById(R.id.description1);
                mListItemTitle1 = (TextView) itemView.findViewById(R.id.title1);

                // Get the view of the elements of second list
                mTextDescription2 = (TextView) itemView.findViewById(R.id.description2);
                mListItemTitle2 = (TextView) itemView.findViewById(R.id.title2);

                // Get the view of the footer elements
                footerTextView = (TextView) itemView.findViewById(R.id.footer);
            }

            public void bindViewSecondList(int pos) {

                if (firstList == null) pos = pos - 1;
                else {
                    if (firstList.size() == 0) pos = pos - 1;
                    else pos = pos - firstList.size() - 2;
                }

                final String description = secondList.get(pos).getDescription();
                final String title = secondList.get(pos).getTitle();

                mTextDescription2.setText(description);
                mListItemTitle2.setText(title);
            }

            public void bindViewFirstList(int pos) {

                // Decrease pos by 1 as there is a header view now.
                pos = pos - 1;

                final String description = firstList.get(pos).getDescription();
                final String title = firstList.get(pos).getTitle();

                mTextDescription1.setText(description);
                mListItemTitle1.setText(title);
            }

            public void bindViewFooter(int pos) {
                footerTextView.setText("This is footer");
            }
        }

        public class FooterViewHolder extends ViewHolder {
            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

        private class FirstListHeaderViewHolder extends ViewHolder {
            public FirstListHeaderViewHolder(View itemView) {
                super(itemView);
            }
        }

        private class FirstListItemViewHolder extends ViewHolder {
            public FirstListItemViewHolder(View itemView) {
                super(itemView);
            }
        }

        private class SecondListHeaderViewHolder extends ViewHolder {
            public SecondListHeaderViewHolder(View itemView) {
                super(itemView);
            }
        }

        private class SecondListItemViewHolder extends ViewHolder {
            public SecondListItemViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            if (viewType == FOOTER_VIEW) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
                FooterViewHolder vh = new FooterViewHolder(v);
                return vh;

            } else if (viewType == FIRST_LIST_ITEM_VIEW) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_first_list, parent, false);
                FirstListItemViewHolder vh = new FirstListItemViewHolder(v);
                return vh;

            } else if (viewType == FIRST_LIST_HEADER_VIEW) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_first_list_header, parent, false);
                FirstListHeaderViewHolder vh = new FirstListHeaderViewHolder(v);
                return vh;

            } else if (viewType == SECOND_LIST_HEADER_VIEW) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_second_list_header, parent, false);
                SecondListHeaderViewHolder vh = new SecondListHeaderViewHolder(v);
                return vh;

            } else {
                // SECOND_LIST_ITEM_VIEW
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_second_list, parent, false);
                SecondListItemViewHolder vh = new SecondListItemViewHolder(v);
                return vh;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            try {
                if (holder instanceof SecondListItemViewHolder) {
                    SecondListItemViewHolder vh = (SecondListItemViewHolder) holder;
                    vh.bindViewSecondList(position);

                } else if (holder instanceof FirstListHeaderViewHolder) {
                    FirstListHeaderViewHolder vh = (FirstListHeaderViewHolder) holder;

                } else if (holder instanceof FirstListItemViewHolder) {
                    FirstListItemViewHolder vh = (FirstListItemViewHolder) holder;
                    vh.bindViewFirstList(position);

                } else if (holder instanceof SecondListHeaderViewHolder) {
                    SecondListHeaderViewHolder vh = (SecondListHeaderViewHolder) holder;

                } else if (holder instanceof FooterViewHolder) {
                    FooterViewHolder vh = (FooterViewHolder) holder;
                    vh.bindViewFooter(position);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {

            int firstListSize = 0;
            int secondListSize = 0;

            if (secondList == null && firstList == null) return 0;

            if (secondList != null)
                secondListSize = secondList.size();
            if (firstList != null)
                firstListSize = firstList.size();

            if (secondListSize > 0 && firstListSize > 0)
                return 1 + firstListSize + 1 + secondListSize + 1;   // first list header, first list size, second list header , second list size, footer
            else if (secondListSize > 0 && firstListSize == 0)
                return 1 + secondListSize + 1;                       // second list header, second list size, footer
            else if (secondListSize == 0 && firstListSize > 0)
                return 1 + firstListSize;                            // first list header , first list size
            else return 0;
        }

        @Override
        public int getItemViewType(int position) {

            int firstListSize = 0;
            int secondListSize = 0;

            if (secondList == null && firstList == null)
                return super.getItemViewType(position);

            if (secondList != null)
                secondListSize = secondList.size();
            if (firstList != null)
                firstListSize = firstList.size();

            if (secondListSize > 0 && firstListSize > 0) {
                if (position == 0) return FIRST_LIST_HEADER_VIEW;
                else if (position == firstListSize + 1)
                    return SECOND_LIST_HEADER_VIEW;
                else if (position == secondListSize + 1 + firstListSize + 1)
                    return FOOTER_VIEW;
                else if (position > firstListSize + 1)
                    return SECOND_LIST_ITEM_VIEW;
                else return FIRST_LIST_ITEM_VIEW;

            } else if (secondListSize > 0 && firstListSize == 0) {
                if (position == 0) return SECOND_LIST_HEADER_VIEW;
                else if (position == secondListSize + 1) return FOOTER_VIEW;
                else return SECOND_LIST_ITEM_VIEW;

            } else if (secondListSize == 0 && firstListSize > 0) {
                if (position == 0) return FIRST_LIST_HEADER_VIEW;
                else return FIRST_LIST_ITEM_VIEW;
            }

            return super.getItemViewType(position);
        }
    }
}
