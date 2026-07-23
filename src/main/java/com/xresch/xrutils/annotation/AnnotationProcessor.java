package com.xresch.xrutils.annotation;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("*")
//@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class AnnotationProcessor extends AbstractProcessor {
	
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    	//-------------------------------
    	// Variables
        Elements elements = processingEnv.getElementUtils();
    	
        //-------------------------------
    	// Iterate Annotations
        for (TypeElement annotation : annotations) {

            PackageElement annotationPackage = elements.getPackageOf(annotation);

            String annotationPackageName = annotationPackage.getQualifiedName().toString();
           
            String thisPackage = AnnotationProcessor.class.getPackageName();
            
            //------------------------------
            // Filter only annotations of com.xresch.xrutils.annotation
            if( ! thisPackage.equals(annotationPackageName)) { continue; }
            
            //------------------------------
            // Create Registry Class
            createRegistry(roundEnv, annotation, annotationPackageName);
        }

        return false;
    }

    /**************************************************************
     * 
     * @param roundEnv
     * @param annotation
     * @param packageName
     * @param annotationName
     **************************************************************/
	private void createRegistry(
					  RoundEnvironment roundEnv
					, TypeElement annotation
					, String packageName
					){
		
		
        String annotationName = annotation.getSimpleName().toString();
        
		String registryName = annotationName + "Registry";
		
		System.out.print("Create Annotation Registry: " + packageName + "." + registryName);
		
		Set<? extends Element> annotated = roundEnv.getElementsAnnotatedWith(annotation);

		try {

		    JavaFileObject file = processingEnv
						            .getFiler()
						            .createSourceFile(packageName + "." + registryName);

		    
		    try (Writer out = file.openWriter()) {

		    	ArrayList<String> discoveredClasses = new ArrayList<>();
		        out.write("package " + packageName + ";\n\n");
		        out.write("public final class " + registryName + " {\n\n");
		        out.write("    private " + registryName + "() {}\n\n");
		        out.write("    public static final Class<?>[] CLASSES = {\n");

			        boolean first = true;
	
			        for (Element e : annotated) {
	
			            if (!(e instanceof TypeElement type))
			                continue;
	
			            if (!first)
			                out.write(",\n");
	
			            first = false;
	
			            out.write("        " + type.getQualifiedName() +".class");
			            discoveredClasses.add(type.getQualifiedName().toString());
			        }

		        out.write("\n    };\n");

		        out.write("}\n");
		        
		        System.out.println(""+discoveredClasses.toString());
		    }
			
			
		} catch (IOException e) {
			System.out.println("IOException while processing annotations: "+e.getMessage());
			e.printStackTrace();
		} 
	}
}
