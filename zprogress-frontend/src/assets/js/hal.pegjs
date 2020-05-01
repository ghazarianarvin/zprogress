start = hal

hal = cbr _ embedded:embedded? _ links:(km _ links)+ _ affordances:(km _ affordances)? _ cbl _
{
	return {
    	embedded : embedded,
        links: links,
        affordances: affordances
    }
}
embedded = embedded_k cl _ cbr _ data: data _ cbl { return data; }
data = resource:identifier_q cl _ sbr _ elements: elements _ sbl { return {resource: elements}}
elements = element _ km _ elements / element: element { return {element: element}}
element = cbr _ content:content _ cbl { return {content: content}}
content = fields / field
fields = links / field _ km _ fields / field
field = name:identifier_q cl _ value:identifier_q / name:identifier_q cl _ value:element
/ name:identifier_q cl _ value:array / name:identifier_q cl _ value:boolean
{
return {
	name: value
}
}
array = sbr _ field+ _ sbl / sbr _ elements _ sbl
boolean = "true" / "false"
links = link:links_k cl _ elements:elements { return {link: elements}}
affordances = affordances_k cl _ elements

embedded_k = q key:"_embedded" q { return key; }
links_k = q key:"_links" q { return key; }
affordances_k = q key:"_templates" q { return key; }
identifier_q = q string:[ a-zA-Z0-9\-:\/]+ q { return string.join(""); }

cbr = "{"
cbl = "}"
sbr = "["
sbl = "]"
cl = ":"
q = "\""
km = ","

_ "whitespace"
  = spaces:[ \t\n\r]* {return " "; }
