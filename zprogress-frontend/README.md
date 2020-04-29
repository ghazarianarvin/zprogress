hal parser grammer:
start = hal

hal = cbr _ embedded? _ (km _ links)+ _ (km _ affordances)? _ cbl _
embedded = embedded_k cl _ cbr _ data _ cbl
data = resource:identifier_q cl _ sbr _ elements _ sbl
// {...}, {...},{...},{...} 
elements = element _ km _ elements / element
// {...}
element = cbr _ content _ cbl
content = fields / field
fields = links / field _ km _ fields / field
field = identifier_q cl _ identifier_q / identifier_q cl _ element / identifier_q cl _ array / identifier_q cl _ boolean
array = sbr _ field+ _ sbl / sbr _ elements _ sbl
boolean = "true" / "false"
links = links_k cl _ elements
affordances = affordances_k cl _ elements

embedded_k = q "_embedded" q
links_k = q "_links" q
affordances_k = q "_templates" q
identifier= char:.+ { return char; }
identifier_q = q identifier q
cbr = "{"
cbl = "}"
sbr = "["
sbl = "]"
cl = ":"
q = "\""
km = ","

_ "whitespace"
  = [ \t\n\r]*

== Example of an empty json == 


{
    "_links": {
        "profiles": {
            "href": "http://localhost:8080/goals/profile"
        },
        "self": {
            "href": "http://localhost:8080/goals"
        }
    },
    "_templates": {
        "default": {
            "method": "post",
            "properties": [
                {
                    "name": "deadline",
                    "required": true
                },
                {
                    "name": "description",
                    "required": true
                },
                {
                    "name": "name",
                    "required": true
                }
            ]
        },
        "putGoal": {
            "method": "put",
            "properties": [
                {
                    "name": "deadline",
                    "required": true
                },
                {
                    "name": "description",
                    "required": true
                },
                {
                    "name": "name",
                    "required": true
                }
            ]
        }
    }
}

== parse completion == 
{
 "_embedded": { 
   "goal": [ 
     { 
      "nested": {"xx": "yy"},
      "dd": "hi", 
      "xx": "blub", 
      "mm": "hihi",
                      "_links": {
                    "steps": {
                        "href": "xxx"
                    },
                     "resource": {
                        "href": "yy"
                       }
                }
     },
     { 
      "dd": "hi", 
      "xx": "blub", 
      "mm": "hihi" 
     } 
    ] 
  }, "_links": { 
         "self": "xxx" 
     }
,
    "_templates": {
        "default": {
            "method": "post",
            "properties": [
                {
                    "name": "deadline",
                    "required": true
                },
                {
                    "name": "description",
                    "required": true
                },
                {
                    "name": "name",
                    "required": true
                }
            ]
        },
        "putGoal": {
            "method": "put",
            "properties": [
                {
                    "name": "deadline",
                    "required": true
                },
                {
                    "name": "description",
                    "required": true
                },
                {
                    "name": "name",
                    "required": true
                }
            ]
        }
    }
}
