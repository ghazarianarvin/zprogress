{
class Element {
	constructor(fields, links) {
    	this.fields = fields
        this.links = links
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

  addNewLink(link) {
  	this.elements.push(link)
  }

  profileLink() {
  	this.elements.forEach(e => {
    	if (e.rel === 'profiles') {
        	return e;
        }
    })
  }
}
}

start = "{" embedded_k ":{" resource: resource "}" links:(","links)? affordances:(","affordances)?"}"
{
	resource.links = links[1]
    resource.affordances = affordances[1]
	return resource;
}

resource = resourceName: identifier_q ":[" elements: elements "]" { return {resource: resourceName, elements: elements} }
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
field = name:identifier_q ":" value:(identifier_q / null / boolean) ","? { return new Field(name, value);}
links = links_k ":{" links: (identifier_q ":{" href_k ":" identifier_q "}" ","?)+ "}"
{
	var l = new Links()
    links.forEach(e => {
    	l.addNewLink({rel: e[0], url: e[4]})
    })

	return l
}
affordances = affordances_k ":{" actions:(action ","?)+ "}"
{
	var ret = []
    actions.forEach(a => ret.push(a[0]))
    return ret
}
action = identifier_q ":{" action: actionProperties "}" { return action }
actionProperties = method_k ":" method: identifier_q "," properties_k ":[" elements+ "]" { return method }


embedded_k = q key:"_embedded" q { return key }
links_k = q key:"_links" q { return key }
href_k = q key: "href" q { return key }
affordances_k = q key:"_templates" q { return key }
method_k = q "method" q
properties_k = q "properties" q

null = "null"
boolean = "true" / "false"
identifier_q = q string:[ a-zA-Z0-9\-:\/]+ q { return string.join("") }

cbl = "{"
cbr = "}"
sbo = "["
sbc = "]"
cl = ":"
q = "\""
km = ","

_ "whitespace"
  = spaces:[ \t\n\r]* {return " " }
