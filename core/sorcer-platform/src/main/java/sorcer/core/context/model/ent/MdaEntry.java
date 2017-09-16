/*
 * Copyright 2013 the original author or authors.
 * Copyright 2013 SorcerSoft.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorcer.core.context.model.ent;

import sorcer.core.signature.ObjectSignature;
import sorcer.service.*;
import sorcer.service.modeling.Model;
import sorcer.service.Domain;
import sorcer.service.modeling.Functionality;

/**
 * Created by Mike Soblewski on 06/03/16.
 */
public class MdaEntry extends Entry<String, Mda> implements Mda {

    private String name;

    private Domain model;

    private Signature signature;

    public MdaEntry(String name, Mda mda) throws EvaluationException {
        this.name = name;
        this.item = mda;
        this.type = Functionality.Type.MDA;
    }

    public MdaEntry(String name, Signature signature) throws EvaluationException {
        this.name = name;
        this.signature = signature;
        this.type = Functionality.Type.MDA;
    }

    public MdaEntry(String name, Mda mda, Context context) throws EvaluationException {
        this.name = name;
        scope = context;
        this.item = mda;
        this.type = Functionality.Type.MDA;
    }

    public Mda getMda() {
        return item;
    }

    public Signature getSignature() {
        return signature;
    }

    @Override
    public void analyze(Model model, Context context) throws EvaluationException {
        try {
            if (item != null && item instanceof Mda) {
                item.analyze(model, context);
            } else if (signature != null) {
                item = (Mda) ((ObjectSignature)signature).initInstance();
                item.analyze(model, context);
            } else if (item == null) {
                throw new InvocationException("No MDA anslysis available!");
            }
        } catch (ContextException | SignatureException e) {
            e.printStackTrace();
        }
    }
}
