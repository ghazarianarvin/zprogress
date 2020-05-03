{
      class Resource {
      	constructor(resource, elements) {
          this.resource = resource
          this.elements = elements
          this.links
          this.affordances
        }

        canCreateResource() {
          return this.canDo("post")
        }


        canUpdateResource() {
          return this.canDo("put")
        }

        canDo(method) {
          let i = 0, len = this.affordances.length;
          for (; i < len; i++) {
            var affordance = this.affordances[i]
            if (affordance.method === method) {
              return true
            }
          }
          return false
        }
      }

    class Affordance {
      constructor(method, fieldMetadata) {
        this.method = method
        this.fieldMetadata = fieldMetadata
      }
    }

    class FieldMetaData {
      constructor() {
        this.name
        this.regex
        this.required
        this.prompt
        this.isDate
      }
    }

    class Element {
      constructor(fields, links) {
        this.fields = fields
        this.links = links
        this.affordance
        this.subElements
      }

      getValueOfField(name) {
        let i = 0, len = this.fields.length;
        for (; i < len; i++) {
          if (this.fields[i].name === name) {
            return this.fields[i].value
          }
        }
      }

      getLinkByRel(rel) {
        return this.links.getLinkByRel(rel)
      }

      canCreateResource() {
        return this.canDo("post")
      }


      canUpdateResource() {
        return this.canDo("put")
      }

      canDo(method) {
        if (this.affordance.method === method) {
          return true
        }
        return false
      }
   }

    class Field {
      constructor(name, value) {
        this.name = name
        this.value = value
      }
    }

    class Links {
      constructor() {
        this.elements = []
      }

      getNonSelfLinks() {
        var nonSelfLinks = []
        this.elements.forEach(l => {
          if (l.rel !== 'self') {
            nonSelfLinks.push(l.url)
          }
        })
        return nonSelfLinks
      }

      addNewLink(link) {
        this.elements.push(link)
      }

        getLinkByRel(rel) {
          let i = 0, len = this.elements.length;
          for (; i < len; i++) {
            if (this.elements[i].rel === rel) {
              return this.elements[i].url;
            }
          }
        }
    }
}

start = "{" result: (collection / single) "}" {return result }

single = fields: (field+) links: links? "," ? affordances: affordances
{
	var element = new Element(fields, links)
    element.affordance = affordances[0]
    return element
}

collection =  embedded_k ":{" resource: resource "}" links:(","links)? affordances:(","affordances)?
{
	resource.links = links[1]
  	resource.affordances = affordances[1]
	return resource;
}

resource = resourceName: identifier_q ":[" elements: elements "]" { return new Resource(resourceName, elements); }
elements = element:("{" (field+) links? "}" ","?)+
{
  var ret = []
  element.forEach(e => {

    if (e && Array.isArray(e)) {
      var fields
      var links
      e.forEach(f => {
        if (f && typeof f !== 'string') {
          if (Array.isArray(f)) { // fields
            fields = f
          }
          if (typeof f === 'object') { // link
            links = f
          }
        }
      })
      ret.push(new Element(fields, links))
    }

  });

  return ret;
}
field = name:identifier_q ":" value:(identifier_q / null / boolean / number) ","? { return new Field(name, value);}
links = links_k ":{" links: (identifier_q ":{" href_k ":" identifier_q "}" ","?)+ "}"
{
  var l = new Links()
  links.forEach(e => {
    l.addNewLink({rel: e[0], url: e[4]})
  })

  return l
}
affordances = affordances_k ":{" affordances:(affordance ","?)+ "}"
{
  var affordancesList = []
  affordances.forEach(a => affordancesList.push(a[0]))
  return affordancesList
}
affordance = identifier_q ":{" affordance: methodAndMetadata "}" { return affordance }
methodAndMetadata = method_k ":" method: identifier_q "," properties_k ":[" metadata: metadata+ "]" { return new Affordance (method, metadata) }
metadata = metadata: ("{" field+ "}" ","?)
{
  var fieldMetadata = new FieldMetaData()
  var mdFields = metadata[1]
  let i = 0, len = mdFields.length
  for (; i < len; i++) {
    if (mdFields[i].name === "name") {
      fieldMetadata.name = mdFields[i].value
      if (fieldMetadata.name.startsWith("d_") || fieldMetadata.name.startsWith("df_") || fieldMetadata.name.startsWith("dp_")) {
        fieldMetadata.isDate = true
      } else {
        fieldMetadata.isDate = false
      }
    } else if (mdFields[i].name === "required") {
      fieldMetadata.required = mdFields[i].value
    } else if (mdFields[i].name === "regex") {
      fieldMetadata.regex = mdFields[i].value
    } else if (mdFields[i].name === "prompt") {
      fieldMetadata.prompt = mdFields[i].value
    }
  }
  return fieldMetadata
}

embedded_k = q key:"_embedded" q { return key }
links_k = q key:"_links" q { return key }
href_k = q key: "href" q { return key }
affordances_k = q key:"_templates" q { return key }
method_k = q "method" q
properties_k = q "properties" q

null = "null"
boolean = "true" / "false"
identifier_q = q string:[ a-zA-Z0-9\-:\/_\.]+ q { return string.join("") }
number = chars:[0-9]+ frac:numberFraction? { return parseFloat(chars.join('') + frac); }
numberFraction  = "." chars:[0-9]* { return "." + chars.join(''); }
q = "\""

_ "whitespace"
  = spaces:[ \t\n\r]* {return " " }
