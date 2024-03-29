/*******************************************************************************
* Copyright (c) 2024 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*******************************************************************************/


async function restCall(call) {
    var response = await fetch("concurrency/api/" + call);
    console.log(response.status)
        var output = await response.text();
        document.getElementById("outputArea").innerText =  output;
}

