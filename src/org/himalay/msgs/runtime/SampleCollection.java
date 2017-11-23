package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.himalay.msgs.runtime.BinPrimitive;
import org.himalay.msgs.runtime.BinStruct;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.PublicBinMsg;

/**
 * This is a sample collection. It is not being used as of now.
 * http://www.artima.com/weblogs/viewpost.jsp?thread=208860
 * @author krishna
 *
 * @param <T>
 */
public class SampleCollection <T extends BinStruct>  extends ArrayList<T> implements PublicBinMsg{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5925678823582305717L;
//		T template = null;
//		public XDRArray(T template)
//		{
//			this.template = template;
//		}
		
		@SuppressWarnings("unchecked")
		public int read(DataInputStream istream) throws IOException {
			int retVal = 0; 
			int sz	= istream.readInt();
			retVal += 4;
			Class<?> classOfT = returnedClass();
			for (int i = 0; i < sz; i++) {
				T newT;
				try {
					newT = (T) (classOfT.newInstance());
					retVal += newT.read(istream);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
			return retVal;
		}
		
		public Class<?> returnedClass (){
		      return getTypeArguments(SampleCollection.class, getClass()).get(0);
		    }
		/**
		   * Get the actual type arguments a child class has used to extend a generic base class.
		   *
		   * @param baseClass the base class
		   * @param childClass the child class
		   * @return a list of the raw classes for the actual type arguments.
		   */
		  public static <T> List<Class<?>> getTypeArguments(
		    Class<T> baseClass, Class<? extends T> childClass) {
		    Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		    Type type = childClass;
		    // start walking up the inheritance hierarchy until we hit baseClass
		    while (! getClass(type).equals(baseClass)) {
		      if (type instanceof Class) {
		        // there is no useful information for us in raw types, so just keep going.
		        type = ((Class) type).getGenericSuperclass();
		      }
		      else {
		        ParameterizedType parameterizedType = (ParameterizedType) type;
		        Class<?> rawType = (Class) parameterizedType.getRawType();
		  
		        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		        TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
		        for (int i = 0; i < actualTypeArguments.length; i++) {
		          resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
		        }
		  
		        if (!rawType.equals(baseClass)) {
		          type = rawType.getGenericSuperclass();
		        }
		      }
		    }
		  
		    // finally, for each actual type argument provided to baseClass, determine (if possible)
		    // the raw class for that type argument.
		    Type[] actualTypeArguments;
		    if (type instanceof Class) {
		      actualTypeArguments = ((Class) type).getTypeParameters();
		    }
		    else {
		      actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		    }
		    List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		    // resolve types by chasing down type variables.
		    for (Type baseType: actualTypeArguments) {
		      while (resolvedTypes.containsKey(baseType)) {
		        baseType = resolvedTypes.get(baseType);
		      }
		      typeArgumentsAsClasses.add(getClass(baseType));
		    }
		    return typeArgumentsAsClasses;
		  }
		  
		  /**
		   * Get the underlying class for a type, or null if the type is a variable type.
		   * @param type the type
		   * @return the underlying class
		   */
		  public static Class<?> getClass(Type type) {
		    if (type instanceof Class) {
		      return (Class) type;
		    }
		    else if (type instanceof ParameterizedType) {
		      return getClass(((ParameterizedType) type).getRawType());
		    }
		    else if (type instanceof GenericArrayType) {
		      Type componentType = ((GenericArrayType) type).getGenericComponentType();
		      Class<?> componentClass = getClass(componentType);
		      if (componentClass != null ) {
		        return Array.newInstance(componentClass, 0).getClass();
		      }
		      else {
		        return null;
		      }
		    }
		    else {
		      return null;
		    }
		  }
		public int write(DataOutputStream ostream) throws IOException {
			int retVal = 0;
	        // write foldersCount
	        BinPrimitive.writeUI32(ostream, this.size());
	        retVal += 4;
	        for (@SuppressWarnings("unchecked")
			Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
				BinStruct element = iterator.next();
				retVal +=element.write(ostream);
			}
	        return retVal;
		}

		public int dump(DumpContext dc) throws IOException {
				int retVal = 0;
		        dc.indent();
		        dc.getPs().print("ClusterConfig\n");
		        dc.increaseIndent();

		        // write element count
		        dc.getPs()
		          .println("count=" + this.size() + "(0x" +Long.toHexString(this.size()) + ")");
		        
		        retVal += 4;
		        for (@SuppressWarnings("unchecked")
				Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
					BinStruct element = iterator.next();
					retVal +=element.dump(dc);
				}
		        
		        dc.decreaseIndent();
		        return retVal;
			
		}

		public void setMemberSize(int i) {
			// TODO Auto-generated method stub
			
		}

		public int getCount() {
			return size();
		}
	}

