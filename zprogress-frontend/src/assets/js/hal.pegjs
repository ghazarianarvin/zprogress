start = hal

hal = cbr _ embedded:embedded? _ links:(km _ links)+ _ affordances:(km _ affordances)? _ cbl _
{
	return {
    	embedded : embedded,
        links: links,
        affordances: affordances
    }
}
embedded = embedded_k cl _ cbr _ data _ cbl
data = resource:identifier_q cl _ sbr _ elements _ sbl
// {...}, {...},{...},{...}
elements = element _ km _ elements / element
// {...}
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
links = links_k cl _ elements
affordances = affordances_k cl _ elements

embedded_k = q "_embedded" q
links_k = q "_links" q
affordances_k = q "_templates" q
identifier_q = q [ a-zA-Z0-9\-:\/]+ q
cbr = "{"
cbl = "}"
sbr = "["
sbl = "]"
cl = ":"
q = "\""
km = ","

_ "whitespace"
  = [ \t\n\r]*
