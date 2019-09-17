/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.execution.expression.tree;

import static java.util.Objects.requireNonNull;

import com.google.errorprone.annotations.Immutable;
import io.confluent.ksql.parser.NodeLocation;
import java.util.Objects;
import java.util.Optional;

/**
 * Expression representing a column name, e.g. {@code col0} or {@code src.col1}.
 */
@Immutable
public class QualifiedNameReference extends Expression {

  private final QualifiedName name;

  public QualifiedNameReference(final QualifiedName name) {
    this(Optional.empty(), name);
  }

  public QualifiedNameReference(final Optional<NodeLocation> location, final QualifiedName name) {
    super(location);
    this.name = requireNonNull(name, "name");
  }

  public QualifiedName getName() {
    return name;
  }

  @Override
  public <R, C> R accept(final ExpressionVisitor<R, C> visitor, final C context) {
    return visitor.visitQualifiedNameReference(this, context);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final QualifiedNameReference that = (QualifiedNameReference) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
