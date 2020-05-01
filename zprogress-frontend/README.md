{
class Field {
 constructor(name, value) {
  this.name = name;
  this.value = value;
 }
}

class Link {
 constructor(relation, url) {
  this.relation = relation;
  this.url = url;
 }
}
}

start = hal

hal = resource: identifier_q ":[" elements: elements "]" { return {resource: resource, elements: elements};}
elements = element: ("{" (links / field)+ "}" ","?)+ {
 var ret = [];
 element.flat().forEach(e => {
  if (Array.isArray(e)) {
   ret.push({ fields: e })
  }
 });
 return ret;
}
field = name:identifier_q ":" value:(identifier_q / null / boolean) ","? { return new Field(name, value);}
links = links_k ":{" rel: identifier_q ":{" href_k ":" url: identifier_q "}"{ return new Link(rel, url);} 



embedded_k = q key:"_embedded" q { return key; }
links_k = q key:"_links" q { return key; }
href_k = q key: "href" q { return key; }
affordances_k = q key:"_templates" q { return key; }
null = "null"
boolean = "true" / "false"
identifier_q = q string:[ a-zA-Z0-9\-:\/]+ q { return string.join(""); }

cbl = "{"
cbr = "}"
sbo = "["
sbc = "]"
cl = ":"
q = "\""
km = ","

_ "whitespace"
  = spaces:[ \t\n\r]* {return " "; }
