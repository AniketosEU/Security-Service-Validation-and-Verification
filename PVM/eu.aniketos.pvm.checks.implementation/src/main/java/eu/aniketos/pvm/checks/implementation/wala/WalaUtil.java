/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.pvm.checks.implementation.wala;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.slicer.Statement;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.types.Descriptor;
import com.ibm.wala.util.debug.Assertions;
import com.ibm.wala.util.strings.Atom;
import eu.aniketos.pvm.commons.FileUtil;


public class WalaUtil {

    public void checkCallTo(Collection<Statement> slice, String targetFunction, String sliceTrg) {
        boolean found = false;
        for (Statement s : slice) {
            if (s.toString().contains(targetFunction)) {
                found = true;
            }
        }

        FileUtil.addValidationFunction(sliceTrg, found);
    }

    /**
     * Find a a given method in a call graph
     *
     * @param cg
     *          The call graph
     * @param methodName
     *          The name of the method
     * @return The (@Statement) of the method if it could be found. Can be @null
     */
    public LinkedList<Statement> findCallTo(CallGraph cg, String methodName) {
        LinkedList<Statement> calls = new LinkedList<Statement>();
        for (CGNode node : cg) {
            Statement s = findCallTo(node, methodName);
            if (s != null) {
                calls.add(s);
            }
        }
        return calls;
    }

    /**
     * Do not call this directly. Use findCallTo(CallGraph cg, String methodName)
     *
     * @param n
     * @param methodName
     * @return
     */
    private Statement findCallTo(CGNode n, String methodName) {
        IR ir = n.getIR();
        if (ir != null) {
            for (Iterator<SSAInstruction> it = ir.iterateAllInstructions(); it.hasNext();) {
                SSAInstruction s = it.next();
                if (s instanceof com.ibm.wala.ssa.SSAAbstractInvokeInstruction) {
                    com.ibm.wala.ssa.SSAAbstractInvokeInstruction call = (com.ibm.wala.ssa.SSAAbstractInvokeInstruction) s;
                    if (call.getCallSite().getDeclaredTarget().getName().toString().equals(methodName)) {
                        com.ibm.wala.util.intset.IntSet indices = ir.getCallInstructionIndices(call.getCallSite());
                        com.ibm.wala.util.debug.Assertions.productionAssertion(indices.size() == 1, "expected 1 but got " + indices.size());
                        return new com.ibm.wala.ipa.slicer.NormalStatement(n, indices.intIterator().next());
                    }
                }
            }
        }
        return null;
    }

    public boolean findDangerousFunctions(CallGraph cg, String dangerousFunction) {
        LinkedList<Statement> calls = findCallTo(cg, dangerousFunction);
        for (Statement call : calls) {
            FileUtil.addDangerousFunction(dangerousFunction, call.getNode().getMethod().getName().toString(), call.getNode().getMethod()
                                          .getDeclaringClass().getName().toString());
        }
        if(calls.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public CGNode findMainMethod(CallGraph cg) {
        Descriptor d = Descriptor.findOrCreateUTF8("([Ljava/lang/String;)V");
        Atom name = Atom.findOrCreateUnicodeAtom("main");
        for (Iterator<? extends CGNode> it = cg.getSuccNodes(cg.getFakeRootNode()); it.hasNext();) {
            CGNode n = it.next();
            if (n.getMethod().getName().equals(name) && n.getMethod().getDescriptor().equals(d)) {
                return n;
            }
        }
        Assertions.UNREACHABLE("failed to find main() method");
        return null;
    }


}
