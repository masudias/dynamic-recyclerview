I've been in trouble sometimes with `RecyclerView` when multiple lists are needed to be shown in a single page of the application. Its not a very good idea actually to have multiple lists in a single layout but however, the idea of having a `ScrollView` and the lists inside that `ScrollView` is even worse.

I had to implement a `ListView` inside a `ScrollView` once and yes it was not a very good experience. Firstly, my list was not scrolling at all. Then I had to add some code to disable the scrolling when the touch is detected inside the list. It was not a very good idea of solving the actual problem. I had another problem of having a fixed height of the `ListView`. In case of list items with dynamic heights, the solution failed.

Having two lists in the layout, one after one is not a good idea either. As the first list need to have a fixed height.

So, after searching for suggestions about how can I implement two lists in a single layout file, I found most of the developers suggests of having a single list with a header and footer if necessary. Later, I could manage to show two lists in a single `RecyclerView` using my custom Adapter. I thought I should save some of my code for future use and hence, you see this note.

I'm just adding a simple mockup screen to make it clear about what we're trying to achieve. You'll have the detailed description of the walk-through in the wiki section of this project. 



<img src="https://raw.githubusercontent.com/comeondude/dynamic-recyclerview/master/screenshot.png" height="400" width="240">
