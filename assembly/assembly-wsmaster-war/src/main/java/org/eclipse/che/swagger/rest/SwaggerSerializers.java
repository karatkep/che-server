/*
 * Copyright (c) 2012-2021 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.swagger.rest;

import io.swagger.models.Swagger;
import io.swagger.util.Json;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces({MediaType.APPLICATION_JSON})
public class SwaggerSerializers implements MessageBodyWriter<Swagger> {

  public boolean isWriteable(
      Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return Swagger.class.isAssignableFrom(type);
  }

  @Override
  public long getSize(
      Swagger data, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return -1;
  }

  @Override
  public void writeTo(
      Swagger data,
      Class<?> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      MultivaluedMap<String, Object> headers,
      OutputStream out)
      throws IOException {

    out.write(Json.mapper().writeValueAsString(data).getBytes("utf-8"));
  }
}
