// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import org.intellij.sdk.language.psi.SimpleNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class SimpleNamedElementImpl extends ASTWrapperPsiElement implements SimpleNamedElement {

  public SimpleNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  /*@Override
  public @NotNull SearchScope getUseScope() {
    return GlobalSearchScope.allScope(getProject());
  }

  @Override
  public @NotNull GlobalSearchScope getResolveScope() {
    return super.getResolveScope();
  }*/
}
