##**Android DrawablePageIndicator**

Drawable page indicator is compatible with ViewPager from the [Android Support Library](http://developer.android.com/tools/support-library/index.html) 


![enter image description here](https://lh3.googleusercontent.com/J9Rg8BoDugsdyaifJYdzKK8qoPCwKzyur7RWjVxbo-I=s500 "drawablePageIndicator_1.png")

![enter image description here](https://lh3.googleusercontent.com/_rSmPvOGRCAPIHgOONugq6IlUPd9ykfwduMauKPhZpA=s500 "drawablePageIndicator2.png")


## **Usage** ##

1. In your xml layout using widget:

      		<com.augustopicciani.drawablepageindicator.widget.DrawablePagerIndicator
                  android:id="@+id/drawableIndicator"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  app:imageSpacing="8"
                  app:centered="true"
                  />

you can also specify which image should be used when page is selected and when page is not selected:

           app:drawableSelected="@drawable/your_drawable_selected_image"
           app:drawableDefault="@drawable/your_drawable_default_image"
     
     
Do not forget to add custom namespace at the root of your xml file:

          xmlns:app="http://schemas.android.com/apk/res-auto"


2. In your main activity or fragment (*onCreate* or *onCreateView*):

	```
	ViewPager pager = (ViewPager) findViewById(R.id.pager);
	pager.setAdapter(myPagerAdapter);
	
  DrawablePageIndicator drawablePagerIndicator = (DrawablePagerIndicator) findViewById(R.id.drawableIndicator);
  drawablePagerIndicator.setViewPager(pager);
 	```  

 	
## **Including in your project** ##

Simply add dependency to your build.gradle file


 	   repositories {
 	       maven {
              url "https://dl.bintray.com/augustopicciani/maven/"
          }
      }
      
     dependencies {
               compile "com.augustopicciani.drawable_page_indicator:library:1.0"
      }
    

## **Developed By** ##

Augusto Picciani - augustopicciani@gmail.com
 	 	
## **Credits** ##

[Jake Wharton](https://github.com/JakeWharton) that support CirclePageIndicator from which this library is inspirated

## **License** ##

Copyright 2015 Augusto Picciani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


  
  
	
	
	
            
  



