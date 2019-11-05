package gui.jframe;

import java.awt.Component;
import java.awt.Container;
import java.awt.IllegalComponentStateException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * 分析JlayeredPane的源码实现:
 * 
 * @author zuoyang
 *
 */
public class JLayeredPane03 {
	//JlayeredPane重写了contianer.addImpl()方法，这个是导致add()方法，组件层级改变的关键
	public static void main(String[] args) {
		JFrame jf = new JFrame("hello");
		
		
		//JRootPane的setContentPane()方法中:
		//layeredPane.add(contentPane, JLayeredPane.FRAME_CONTENT_LAYER);
		//这个实际调用了Container.add(contentPane, JLayeredPane.FRAME_CONTENT_LAYER)->addImpl(comp, constraints, -1);
		//因为JLayeredPane从写了addImpl()方法，所以这里调用的是JLayeredPane中的方法
		//即:JLayeredPane.addImpl(contentPane, JLayeredPane.FRAME_CONTENT_LAYER,-1)
//		protected void addImpl(Component comp, Object constraints, int index) {
//	        int layer;
//	        int pos;
//
//			constraits作为约束值，这个值是表示层级的一个整数，添加contentPane和JmenuBar的时候使用的是FRAME_CONTENT_LAYER(-30000)
//			JLayeredPane类中定义了这个常量
//	        if(constraints instanceof Integer) {
//	            layer = ((Integer)constraints).intValue();//这里把Integer类型转换为int值
//	            setLayer(comp, layer);//分析1:
//				//这个方法主要作用:把一个组件插入到对应层级中的组件列表中的特定位置
//	        } else
//	            layer = getLayer(comp);
//
//	        pos = insertIndexForLayer(layer, index);
//	        super.addImpl(comp, constraints, pos);
//	        comp.validate();
//	        comp.repaint();
//	        validateOptimizedDrawing();
//	    }
		
		
		
		//分析1:
		//这里分析setlayer()方法
		//当你把一个组件放置到JLayeredPane中的时候，他会创建一个层级，具体在setLayer()方法里面
		//这个层级，通过一个整数来区分，在JlayeredPane中定义了一个常量:public final static Integer FRAME_CONTENT_LAYER = new Integer(-30000);
		//还定义了另外几个层级常量:
//		public final static Integer DEFAULT_LAYER = new Integer(0);
//	    public final static Integer PALETTE_LAYER = new Integer(100);
//	    public final static Integer MODAL_LAYER = new Integer(200);
//	    public final static Integer POPUP_LAYER = new Integer(300);
//	    public final static Integer DRAG_LAYER = new Integer(400);
		    
		//JLayeredPane中添加contentPane和JMenuBar的时候使用的都是这个层级
		//layeredPane.add(menuBar, JLayeredPane.FRAME_CONTENT_LAYER);
		//layeredPane.add(contentPane, JLayeredPane.FRAME_CONTENT_LAYER);
		//我们看一下setLayer(comp, layer);实际调用了setLayer(comp, layer,-1),查看源码:
		//api中的说明:
		//setLayer(component,layer):把组件放置到对应的layer层级，并且是该层级中所有组件的最底层
		//setLayer(component,layer,position):把组件放置到对应的layer层，并且放置到该层级中所有组件列表中的一个特定位置
		//分析源码:
//		public void setLayer(Component c, int layer, int position)  {
//	        Integer layerObj;
//	        layerObj = getObjectForLayer(layer);//把这个int类型的layer值，转换为Integer类型对象
//			需要注意的是:如果这个层级，不在JlayeredPane定义的整数值中，那么会把传入的这个int值转换为Integer对象，作为一个新添加的层级来使用
//			即:程序员可以自己添加一个整数值,来表示这个组件所在的层级
		
//
//	        if(layer == getLayer(c) && position == getPosition(c)) {
//	                repaint(c.getBounds());
//	            return;
//	        }
		
			//把当前这个组件所属的层级，通过一个hash,标记到当前组件里面
//	        /// MAKE SURE THIS AND putLayer(JComponent c, int layer) are SYNCED
//	        if(c instanceof JComponent)
				//这个是JComponent提供hash功能
//	            ((JComponent)c).putClientProperty(LAYER_PROPERTY, layerObj);
//	        else
				//如果当前组件不是JComponent,则自己创建一个hash表用来标记当前的组件所属的层级
//	            getComponentToLayer().put(c, layerObj);
//
		
		
//	        if(c.getParent() == null || c.getParent() != this) {
//	            repaint(c.getBounds());
//	            return;
//	        }
//			
//			//插入操作具体实施:
//	        int index = insertIndexForLayer(c, layer, position);
//	        setComponentZOrder(c, index);//按照container.zorder的管理方式，把组件插入进去
//	        repaint(c.getBounds());
//	    }
		
		
		
		
		//分析一下inserIndexForLayer();
		//把一个组件(comp)插入到对应的层级(layer)中的对应的位置(position)
		//这个方法返回的索引是所有组件的绝对索引值，不考虑layer层级
//		private int insertIndexForLayer(Component comp, int layer, int position) {
//	        int i, count, curLayer;
//	        int layerStart = -1;
//	        int layerEnd = -1;
//	        int componentCount = getComponentCount();//获得当前容器内(JLayeredPane)的所有组件
//
//	        ArrayList<Component> compList =
//	            new ArrayList<Component>(componentCount);
//			//把当前容器内的非(要新添加的组件)comp组件放置到一个ArrayList里面
//	        for (int index = 0; index < componentCount; index++) {
//	            if (getComponent(index) != comp) {
//	                compList.add(getComponent(index));
//	            }
//	        }
//
//	        count = compList.size();
			//遍历已有的组件
//	        for (i = 0; i < count; i++) {
//	            curLayer = getLayer(compList.get(i));//获得已有组件的layer，这是个整数值，通过hash和组件关联的表示层级的整数
//	            if (layerStart == -1 && curLayer == layer) {
//	                layerStart = i;//如果已有的组件和当前要添加的组件层级相同，则记录下这个已有组件的遍历索引
//					//这个索引是已有组件中的第一个 和 要操作的组件的层级相同 的索引
//	            }
//	            if (curLayer < layer) {//要添加的组件的层级大于当前被遍历的一个组件的层级
//	                if (i == 0) {
						//说明，刚开始遍历已有组件，则第一个已有组件的层级就小于，要新添加操作的组件的层级
//	                    // layer is greater than any current layer
//	                    // [ ASSERT(layer > highestLayer()) ]
//	                    layerStart = 0;
//	                    layerEnd = 0;
//	                } else {
//						//此时,要添加的组件大于其中一个被遍历的组件的层级，此时记录这个被遍历组件在已有组件的索引
//	                    layerEnd = i;
//	                }
//	                break;
//	            }
//	        }
//
//			// 要添加的组件的层级小于所有现有组件的层级
//	        // layer requested is lower than any current layer
//	        // [ ASSERT(layer < lowestLayer()) ]
//	        // put it on the bottom of the stack
//	        if (layerStart == -1 && layerEnd == -1)
//	            return count;//返回count,表示现有组件集合的最后一个索引，此时新组件被添加到最下面
//
//			// 要添加的组件的层级和一个被遍历的组件的层级相同
//	        // In the case of a single layer entry handle the degenerative cases
//	        if (layerStart != -1 && layerEnd == -1)
//	            layerEnd = count;
//
//			// 此时新添加的组件 大于 其中一个组件的层级，
//	        if (layerEnd != -1 && layerStart == -1)
//	            layerStart = layerEnd;
//
//	        // If we are adding to the bottom, return the last element
//	        if (position == -1)
//	            return layerEnd;
//
//	        // Otherwise make sure the requested position falls in the
//	        // proper range
//	        if (position > -1 && layerStart + position <= layerEnd)
//	            return layerStart + position;
//
//	        // Otherwise return the end of the layer
//	        return layerEnd;
//	    }
		
		
		
		
		
		
		
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

}