// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.ProcessingContext;
import com.tang.intellij.lua.psi.*;
import com.tang.intellij.lua.psi.impl.LuaLiteralExprImpl;
import org.jetbrains.annotations.NotNull;

import static org.intellij.sdk.language.SimpleAnnotator.SIMPLE_PREFIX_STR;
import static org.intellij.sdk.language.SimpleAnnotator.SIMPLE_SEPARATOR_STR;

final class SimpleReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns
                        .psiElement(LuaLiteralExpr.class)
                        .withParent(LuaListArgs.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        LuaLiteralExprImpl literalExpression = (LuaLiteralExprImpl) element;
                        var child = (LeafPsiElement) literalExpression.getFirstChild();
                        if (child == null || child.getElementType() != LuaTypes.STRING) {
                            return PsiReference.EMPTY_ARRAY;
                        }
                        String value = child.getText().substring(1, child.getTextLength() - 1);
                        if ((value != null && value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR))) {
                            TextRange property = new TextRange(SIMPLE_PREFIX_STR.length() + SIMPLE_SEPARATOR_STR.length() + 1,
                                    value.length() + 1);
                            return new PsiReference[]{new SimpleReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }



}
