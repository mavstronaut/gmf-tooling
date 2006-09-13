package org.eclipse.gmf.codegen.templates.editor;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;
import java.util.*;
import org.eclipse.emf.codegen.util.*;

public class NavigatorContentProviderGenerator
{
  protected static String nl;
  public static synchronized NavigatorContentProviderGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    NavigatorContentProviderGenerator result = new NavigatorContentProviderGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "/*" + NL + " *";
  protected final String TEXT_3 = NL + " */";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_6 = " implements ";
  protected final String TEXT_7 = " {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final Object[] EMPTY_ARRAY = new Object[0];" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public void dispose() {" + NL + "    }" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public void inputChanged(";
  protected final String TEXT_8 = " viewer, Object oldInput, Object newInput) {" + NL + "    }" + NL + "    " + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object[] getElements(Object inputElement) {" + NL + "\t\treturn getChildren(inputElement);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public Object[] getChildren(Object parentElement) {" + NL + "    \tif (parentElement instanceof ";
  protected final String TEXT_9 = ") {" + NL + "\t\t\t";
  protected final String TEXT_10 = " view = (";
  protected final String TEXT_11 = ") parentElement;" + NL + "\t\t\tif (!";
  protected final String TEXT_12 = ".MODEL_ID.equals(";
  protected final String TEXT_13 = ".getModelID(view))) {" + NL + "\t\t\t\treturn EMPTY_ARRAY;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tint visualID = ";
  protected final String TEXT_14 = ".getVisualID(view);" + NL + "\t\t\tswitch (visualID) {";
  protected final String TEXT_15 = NL + "\t\t\tcase ";
  protected final String TEXT_16 = ".VISUAL_ID: {" + NL + "\t\t\t\t";
  protected final String TEXT_17 = " result = new ";
  protected final String TEXT_18 = "();";
  protected final String TEXT_19 = NL + "\t\t\t\t";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = " = new ";
  protected final String TEXT_22 = "(\"";
  protected final String TEXT_23 = "\", \"";
  protected final String TEXT_24 = "\", ";
  protected final String TEXT_25 = ".MODEL_ID, view);";
  protected final String TEXT_26 = NL + "\t\t\t\t";
  protected final String TEXT_27 = ".addChildren(";
  protected final String TEXT_28 = NL + "\t\t\t\tresult.addAll(";
  protected final String TEXT_29 = "getChildByType(view.getChildren(), ";
  protected final String TEXT_30 = ".getType(";
  protected final String TEXT_31 = ".VISUAL_ID))";
  protected final String TEXT_32 = "getViewByType(view.eResource().getContents(), ";
  protected final String TEXT_33 = ".MODEL_ID)";
  protected final String TEXT_34 = "getViewByType(view.getDiagram().getChildren(), ";
  protected final String TEXT_35 = ".getType(";
  protected final String TEXT_36 = ".VISUAL_ID))";
  protected final String TEXT_37 = "getViewByType(view.getDiagram().getEdges(), ";
  protected final String TEXT_38 = ".getType(";
  protected final String TEXT_39 = ".VISUAL_ID))";
  protected final String TEXT_40 = ".EMPTY_LIST";
  protected final String TEXT_41 = "getConnectedViews(view, ";
  protected final String TEXT_42 = ".getType(";
  protected final String TEXT_43 = ".VISUAL_ID), ";
  protected final String TEXT_44 = ")";
  protected final String TEXT_45 = ".EMPTY_LIST";
  protected final String TEXT_46 = ".EMPTY_LIST";
  protected final String TEXT_47 = ");";
  protected final String TEXT_48 = NL + "\t\t\tif (!";
  protected final String TEXT_49 = ".isEmpty()) {";
  protected final String TEXT_50 = NL + "\t\t\tresult.add(";
  protected final String TEXT_51 = ");";
  protected final String TEXT_52 = NL + "\t\t\t}";
  protected final String TEXT_53 = NL + "\t\t\treturn result.toArray();" + NL + "\t\t\t}";
  protected final String TEXT_54 = NL + "\t\t\t}" + NL + "    \t} else if (parentElement instanceof ";
  protected final String TEXT_55 = ") {" + NL + "\t    \t";
  protected final String TEXT_56 = " file = (";
  protected final String TEXT_57 = ") parentElement;" + NL + "\t\t\t";
  protected final String TEXT_58 = " editingDomain = ";
  protected final String TEXT_59 = ".INSTANCE.createEditingDomain();" + NL + "\t\t\t";
  protected final String TEXT_60 = " resourceSet = editingDomain.getResourceSet();" + NL + "\t\t\t";
  protected final String TEXT_61 = " fileURI = ";
  protected final String TEXT_62 = ".createPlatformResourceURI(file.getFullPath().toString());" + NL + "\t\t\t";
  protected final String TEXT_63 = " resource = resourceSet.getResource(fileURI, true);" + NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_64 = " result = new ";
  protected final String TEXT_65 = "();";
  protected final String TEXT_66 = NL + "\t\t\t";
  protected final String TEXT_67 = " ";
  protected final String TEXT_68 = " = new ";
  protected final String TEXT_69 = "(\"";
  protected final String TEXT_70 = "\", \"";
  protected final String TEXT_71 = "\", ";
  protected final String TEXT_72 = ".MODEL_ID, file);";
  protected final String TEXT_73 = NL + "\t\t\t";
  protected final String TEXT_74 = ".addChildren(";
  protected final String TEXT_75 = NL + "\t\t\tresult.addAll(";
  protected final String TEXT_76 = "getViewByType(resource.getContents(), ";
  protected final String TEXT_77 = ".MODEL_ID)";
  protected final String TEXT_78 = ".EMPTY_LIST";
  protected final String TEXT_79 = "getChildByType(resource.getContents(), ";
  protected final String TEXT_80 = ".getType(";
  protected final String TEXT_81 = ".VISUAL_ID))";
  protected final String TEXT_82 = ".EMPTY_LIST";
  protected final String TEXT_83 = ");";
  protected final String TEXT_84 = NL + "\t\t\tif (!";
  protected final String TEXT_85 = ".isEmpty()) {";
  protected final String TEXT_86 = NL + "\t\t\tresult.add(";
  protected final String TEXT_87 = ");";
  protected final String TEXT_88 = NL + "\t\t\t}";
  protected final String TEXT_89 = NL + "\t\t\treturn result.toArray();" + NL + "\t\t} else if (parentElement instanceof ";
  protected final String TEXT_90 = ") {" + NL + "\t\t\t";
  protected final String TEXT_91 = " group = (";
  protected final String TEXT_92 = ") parentElement;" + NL + "\t\t\tif (";
  protected final String TEXT_93 = ".MODEL_ID.equals(group.getModelID())) {" + NL + "\t\t\t\treturn group.getChildren();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn EMPTY_ARRAY;" + NL + "    }" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public Object getParent(Object element) {" + NL + "\t\tif (element instanceof ";
  protected final String TEXT_94 = ") {" + NL + "\t\t\t";
  protected final String TEXT_95 = " view = (";
  protected final String TEXT_96 = ") element;" + NL + "\t\t\tif (!";
  protected final String TEXT_97 = ".MODEL_ID.equals(";
  protected final String TEXT_98 = ".getModelID(view))) {" + NL + "\t\t\t\treturn null;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tint visualID = ";
  protected final String TEXT_99 = ".getVisualID(view);" + NL + "\t\t\tswitch (visualID) {";
  protected final String TEXT_100 = NL + "\t\t\tcase ";
  protected final String TEXT_101 = ".VISUAL_ID: { ";
  protected final String TEXT_102 = NL + "\t\t\t\t";
  protected final String TEXT_103 = " group = new ";
  protected final String TEXT_104 = "(\"";
  protected final String TEXT_105 = "\", \"";
  protected final String TEXT_106 = "\", ";
  protected final String TEXT_107 = ".MODEL_ID, ";
  protected final String TEXT_108 = NL + "\t\t\t\treturn ";
  protected final String TEXT_109 = "view.eContainer()";
  protected final String TEXT_110 = ".getFile(view.eResource())";
  protected final String TEXT_111 = ");" + NL + "\t\t\t\tgroup.addChild(view);" + NL + "\t\t\t\treturn group;";
  protected final String TEXT_112 = ";";
  protected final String TEXT_113 = "\t\t\t" + NL + "\t\t\t}";
  protected final String TEXT_114 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_115 = " " + NL + "\t\telse if (element instanceof ";
  protected final String TEXT_116 = ") {" + NL + "\t\t\t";
  protected final String TEXT_117 = " group = (";
  protected final String TEXT_118 = ") element;" + NL + "\t\t\tif (";
  protected final String TEXT_119 = ".MODEL_ID.equals(group.getModelID())) {" + NL + "\t\t\t\treturn group.getParent();" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_120 = NL + "\t\treturn null;" + NL + "    }" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public boolean hasChildren(Object element) {" + NL + "    \treturn element instanceof ";
  protected final String TEXT_121 = " || getChildren(element).length > 0;" + NL + "    }" + NL + "    " + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void init(";
  protected final String TEXT_122 = " aConfig) {" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void restoreState(";
  protected final String TEXT_123 = " aMemento) {" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void saveState(";
  protected final String TEXT_124 = " aMemento) {" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_125 = " getViewByType(";
  protected final String TEXT_126 = " childViews, String type) {" + NL + "\t\t";
  protected final String TEXT_127 = " result = new ";
  protected final String TEXT_128 = "();" + NL + "\t\tfor (";
  protected final String TEXT_129 = " it = childViews.iterator(); it.hasNext();) {" + NL + "\t\t\tObject next = it.next();" + NL + "\t\t\tif (false == next instanceof ";
  protected final String TEXT_130 = ") {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_131 = " nextView = (";
  protected final String TEXT_132 = ") next;" + NL + "\t\t\tif (type.equals(nextView.getType())) {" + NL + "\t\t\t\tresult.add(nextView);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_133 = " getChildByType(";
  protected final String TEXT_134 = " childViews, String type) {" + NL + "\t\t";
  protected final String TEXT_135 = " result = new ";
  protected final String TEXT_136 = "();" + NL + "\t\t";
  protected final String TEXT_137 = " children = new ";
  protected final String TEXT_138 = "(childViews);" + NL + "\t\tfor (int i = 0; i < children.size(); i++) {" + NL + "\t\t\tif (false == children.get(i) instanceof ";
  protected final String TEXT_139 = ") {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_140 = " nextChild = (";
  protected final String TEXT_141 = ") children.get(i);" + NL + "\t\t\tif (type.equals(nextChild.getType())) {" + NL + "\t\t\t\tresult.add(nextChild);" + NL + "\t\t\t} else if (!stopGettingChildren(nextChild, type)) {" + NL + "\t\t\t\tchildren.addAll(nextChild.getChildren());" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean stopGettingChildren(";
  protected final String TEXT_142 = " child, String type) {" + NL + "\t\treturn false;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_143 = " getConnectedViews(";
  protected final String TEXT_144 = " rootView, String type, boolean isOutTarget) {" + NL + "\t\t";
  protected final String TEXT_145 = " result = new ";
  protected final String TEXT_146 = "();" + NL + "\t\t";
  protected final String TEXT_147 = " connectedViews = new ";
  protected final String TEXT_148 = "();" + NL + "\t\tconnectedViews.add(rootView);" + NL + "\t\t";
  protected final String TEXT_149 = " visitedViews = new ";
  protected final String TEXT_150 = "();" + NL + "\t\tfor (int i = 0; i < connectedViews.size(); i++) {" + NL + "\t\t\t";
  protected final String TEXT_151 = " nextView = (";
  protected final String TEXT_152 = ") connectedViews.get(i);" + NL + "\t\t\tif (visitedViews.contains(nextView)) {" + NL + "\t\t\t\tcontinue;" + NL + "\t\t\t}" + NL + "\t\t\tvisitedViews.add(nextView);" + NL + "\t\t\tif (type.equals(nextView.getType()) && nextView != rootView) {" + NL + "\t\t\t\tresult.add(nextView);" + NL + "\t\t\t} else {" + NL + "\t\t\t\tif (isOutTarget && !stopGettingOutTarget(nextView, rootView, type)) {" + NL + "\t\t\t\t\tconnectedViews.addAll(nextView.getSourceEdges());" + NL + "\t\t\t\t\tif (nextView instanceof ";
  protected final String TEXT_153 = ") {" + NL + "\t\t\t\t\t\tconnectedViews.add(((Edge) nextView).getTarget());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t} " + NL + "\t\t\t\tif (!isOutTarget && !stopGettingInSource(nextView, rootView, type)) {" + NL + "\t\t\t\t\tconnectedViews.addAll(nextView.getTargetEdges());" + NL + "\t\t\t\t\tif (nextView instanceof ";
  protected final String TEXT_154 = ") {" + NL + "\t\t\t\t\t\tconnectedViews.add(((Edge) nextView).getSource());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean stopGettingInSource(";
  protected final String TEXT_155 = " nextView, ";
  protected final String TEXT_156 = " rootView, String type) {" + NL + "\t\treturn !isOneHopConnection(nextView, rootView);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean stopGettingOutTarget(";
  protected final String TEXT_157 = " nextView, ";
  protected final String TEXT_158 = " rootView, String type) {" + NL + "\t\treturn !isOneHopConnection(nextView, rootView);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isOneHopConnection(";
  protected final String TEXT_159 = " targetView, ";
  protected final String TEXT_160 = " sourceView) {" + NL + "\t\tif (sourceView == targetView) {" + NL + "\t\t\treturn true;" + NL + "\t\t}" + NL + "\t\tif (sourceView instanceof ";
  protected final String TEXT_161 = ") {" + NL + "\t\t\treturn targetView instanceof ";
  protected final String TEXT_162 = ";" + NL + "\t\t} if (sourceView instanceof ";
  protected final String TEXT_163 = ") {" + NL + "\t\t\treturn targetView instanceof ";
  protected final String TEXT_164 = ";" + NL + "\t\t}" + NL + "\t\treturn false;" + NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_165 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
GenNavigator genNavigator = (GenNavigator) ((Object[]) argument)[0];
GenDiagram genDiagram = genNavigator.getEditorGen().getDiagram();
Collection commonBaseElements = new ArrayList(genDiagram.getAllContainers());
commonBaseElements.addAll(genDiagram.getLinks());
final ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];

    stringBuffer.append(TEXT_1);
    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n *"));
    stringBuffer.append(TEXT_3);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_4);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genNavigator.getContentProviderClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.navigator.ICommonContentProvider"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName("org.eclipse.jface.viewers.Viewer"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_14);
    for (Iterator containers = commonBaseElements.iterator(); containers.hasNext();) {
	GenCommonBase nextCommonBase = (GenCommonBase) containers.next();
	Collection childReferences = genNavigator.getChildReferencesFrom(nextCommonBase);
	if (childReferences.size() == 0) {
		continue;
	}
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName(nextCommonBase.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_18);
    	Map createdGroups = new HashMap();
	for (Iterator it = childReferences.iterator(); it.hasNext();) {
		GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
		if (nextReference.isInsideGroup()) {
			String groupNameKey = CodeGenUtil.validJavaIdentifier(nextReference.getGroupName());
			if (!createdGroups.containsKey(groupNameKey)) {
				createdGroups.put(groupNameKey, nextReference);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(nextReference.getGroupName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(nextReference.getGroupIcon());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_25);
    			}
    stringBuffer.append(TEXT_26);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_27);
    
		} else {
    stringBuffer.append(TEXT_28);
    
		}
		
		if (nextReference.getReferenceType() == GenNavigatorReferenceType.CHILDREN_LITERAL) {
			
    stringBuffer.append(TEXT_29);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_31);
    
		} else if (nextReference.getReferenceType() == GenNavigatorReferenceType.DEFAULT_LITERAL) {
			if (nextReference.getChild() instanceof GenDiagram) {
			
    stringBuffer.append(TEXT_32);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_33);
    
			} else if (nextReference.getChild() instanceof GenTopLevelNode) {
			
    stringBuffer.append(TEXT_34);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_36);
    
			} else if (nextReference.getChild() instanceof GenLink) {
			
    stringBuffer.append(TEXT_37);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_39);
    
			} else {
			/* Any other "Default" child refrences are not supported */
			
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_40);
    
			}
		} else if (nextReference.getReferenceType() == GenNavigatorReferenceType.OUT_TAGET_LITERAL || nextReference.getReferenceType() == GenNavigatorReferenceType.IN_SOURCE_LITERAL) {
			boolean isOutTarget = nextReference.getReferenceType() == GenNavigatorReferenceType.OUT_TAGET_LITERAL;
			if (nextCommonBase instanceof GenNode || nextCommonBase instanceof GenLink) {
			
    stringBuffer.append(TEXT_41);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(isOutTarget);
    stringBuffer.append(TEXT_44);
    
			} else {
			/* Any other "OUT_TAGET_LITERAL || IN_SOURCE_LITERAL" child refrences are not supported */
			
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_45);
    
			}
		} else {
			/* Put processing of other types of the references here. */
			
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_46);
    
		}
    stringBuffer.append(TEXT_47);
    	}
	
	for (Iterator it = createdGroups.keySet().iterator(); it.hasNext();) {
		String nextGroupKey = (String) it.next();
		GenNavigatorChildReference nextReference = (GenNavigatorChildReference) createdGroups.get(nextGroupKey);
		if (nextReference.isHideIfEmpty()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(nextGroupKey);
    stringBuffer.append(TEXT_49);
    		}
    stringBuffer.append(TEXT_50);
    stringBuffer.append(nextGroupKey);
    stringBuffer.append(TEXT_51);
    		if (nextReference.isHideIfEmpty()) {
    stringBuffer.append(TEXT_52);
    		}
	}
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.resources.IFile"));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.resources.IFile"));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.resources.IFile"));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.edit.domain.EditingDomain"));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory"));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.resource.ResourceSet"));
    stringBuffer.append(TEXT_60);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_61);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_65);
    Map createdDiagramGroups = new HashMap();
for (Iterator it = genNavigator.getChildReferencesFrom(null).iterator(); it.hasNext();) {
	GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
	if (nextReference.isInsideGroup()) {
		String groupNameKey = CodeGenUtil.validJavaIdentifier(nextReference.getGroupName());
		if (!createdDiagramGroups.containsKey(groupNameKey)) {
			createdDiagramGroups.put(groupNameKey, nextReference);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_69);
    stringBuffer.append(nextReference.getGroupName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(nextReference.getGroupIcon());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_72);
    		}
    stringBuffer.append(TEXT_73);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_74);
    
	} else {
    stringBuffer.append(TEXT_75);
    
	}
	
	if (nextReference.getReferenceType() == GenNavigatorReferenceType.DEFAULT_LITERAL) {
		if (nextReference.getChild() instanceof GenDiagram) {
			
    stringBuffer.append(TEXT_76);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_77);
    
		} else {
			/* Any other "Default" child refrences are not supported for resource */
			
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_78);
    
		}
	} else if (nextReference.getReferenceType() == GenNavigatorReferenceType.CHILDREN_LITERAL) {
		
    stringBuffer.append(TEXT_79);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_80);
    stringBuffer.append(importManager.getImportedName(nextReference.getChild().getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_81);
    
	} else {
		/* Any other ReferenceTypes are not supported for the resource */
		
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_82);
    
	}
    stringBuffer.append(TEXT_83);
    }

for (Iterator it = createdDiagramGroups.keySet().iterator(); it.hasNext();) {
	String groupNameKey = (String) it.next();
	GenNavigatorChildReference nextReference = (GenNavigatorChildReference) createdDiagramGroups.get(groupNameKey);
	if (nextReference.isHideIfEmpty()) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_85);
    	}
    stringBuffer.append(TEXT_86);
    stringBuffer.append(groupNameKey);
    stringBuffer.append(TEXT_87);
    	if (nextReference.isHideIfEmpty()) {
    stringBuffer.append(TEXT_88);
    	}
}

    stringBuffer.append(TEXT_89);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_96);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_97);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_99);
    boolean shouldHandleParentGroups = false;
for (Iterator containers = commonBaseElements.iterator(); containers.hasNext();) {
	GenCommonBase nextCommonBase = (GenCommonBase) containers.next();
	Collection childReferences = genNavigator.getChildReferencesTo(nextCommonBase);
	if (childReferences.size() == 0) {
		continue;
	}
	
	GenNavigatorChildReference theReference = null;
	for (Iterator it = childReferences.iterator(); theReference == null && it.hasNext();) {
		GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
		if (nextReference.getReferenceType() == GenNavigatorReferenceType.CHILDREN_LITERAL) {
			theReference = nextReference;
		}
	}
	
	for (Iterator it = childReferences.iterator(); theReference == null && it.hasNext();) {
		GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
		if (nextReference.getReferenceType() == GenNavigatorReferenceType.DEFAULT_LITERAL) {
			if (nextCommonBase instanceof GenDiagram && nextReference.getParent() == null) {
				theReference = nextReference;
			}
		}
	}
	
	if (theReference == null) {
		/* No supported child references for back navigation found */
		continue;
	}
    stringBuffer.append(TEXT_100);
    stringBuffer.append(importManager.getImportedName(nextCommonBase.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_101);
    	if (theReference.isInsideGroup()) {
		shouldHandleParentGroups = true;
    stringBuffer.append(TEXT_102);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(theReference.getGroupName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(theReference.getGroupIcon());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_107);
    
	} else {
    stringBuffer.append(TEXT_108);
    
	}
	
	if (theReference.getParent() != null) {
		/* Child reference only */
		
    stringBuffer.append(TEXT_109);
    
	} else {
		/* Child reference with parent == null or default reference for diagram */
		
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.workspace.util.WorkspaceSynchronizer"));
    stringBuffer.append(TEXT_110);
    
	}
	
	if (theReference.isInsideGroup()) {
		
    stringBuffer.append(TEXT_111);
    	} else {
		
    stringBuffer.append(TEXT_112);
    	}
    stringBuffer.append(TEXT_113);
    }
    stringBuffer.append(TEXT_114);
    if (shouldHandleParentGroups) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(importManager.getImportedName(genNavigator.getGroupWrapperQualifiedClassName()));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.resources.IFile"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.navigator.ICommonContentExtensionSite"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IMemento"));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IMemento"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_131);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_143);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_146);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_148);
    stringBuffer.append(importManager.getImportedName("java.util.Set"));
    stringBuffer.append(TEXT_149);
    stringBuffer.append(importManager.getImportedName("java.util.HashSet"));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_151);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_152);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_153);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_155);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_157);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Node"));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_162);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Node"));
    stringBuffer.append(TEXT_164);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_165);
    return stringBuffer.toString();
  }
}
