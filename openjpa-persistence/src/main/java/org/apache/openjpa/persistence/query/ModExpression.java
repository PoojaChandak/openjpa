/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openjpa.persistence.query;

/**
 * Denotes MOD(e1, e2) Expression.
 *
 * @author Pinaki Poddar
 *
 */
class ModExpression extends BinaryOperatorExpression {
	
    private static final long serialVersionUID = 1L;

    public ModExpression(ExpressionImpl op, ExpressionImpl op2) {
		super(op, BinaryFunctionalOperator.MOD, op2);
	}

	@Override
	public String asExpression(AliasContext ctx) {
		return _op + "(" + ((Visitable)_e1).asExpression(ctx)
		     + "," + ((Visitable)_e2).asExpression(ctx);
	}
}
