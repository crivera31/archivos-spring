package com.sprinboot.servicios.app.oauth.security;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "@%%$$NuevaClaveCoIngTecno";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAyvulMrLopXdpt5rHCmVJEXKWiV37pM9r1iPH/ZbLF9t/dQow\r\n" + 
			"nFG8yB/qGsIkXme5AckKxVuTQeAprq54EkEFhbaNcV5nJxEdwz0HGLKAeqgcUiYf\r\n" + 
			"NkBiw8IhBvnf2AFdJaskGyL9jP2K9NjzKXjULFiceg8TADWnFB+nJygCJVow3p3x\r\n" + 
			"WIBtKglsDkFrAKRTyk4Q0ztY1tMJVas5KYsQEmpx8dgGNlRLvOThfAVFbWieJyIh\r\n" + 
			"nCLCQwPMNTIN5+uR03//1ybH2Y3y7NGI6kUH3Mh9mamFeOrT2UioVWh05GbIu9Y8\r\n" + 
			"Xs6ekbNlUzigZEbxVpEaJgB45MXo4mbaMG5dqQIDAQABAoIBACpZMBEQxHrpG/RD\r\n" + 
			"ZW1kzkbGmmOUaRDseXoWwv3EPSEAGWpostWaNAGoruXgRUgw6adWs1mTjnh69RdN\r\n" + 
			"rR1Z1XYdLQtO/8RlzMjT7awYabetZSM6ct6VtkVTNiLHdQ0S08UtHH5Q4YdNynTk\r\n" + 
			"4m5vmAycfCD3DibICtCm/e+saC40cGXnE+gGarnbGVz1NN2keTi/fWJ8wmfpF5D3\r\n" + 
			"BknkXC8Yhm5GKL0SdjEPH3o70IRresHuFFa8PEOUoBOo5ZMxbXtCo0YlCqfhD40Z\r\n" + 
			"afEz4QaesSViysl8M+1PbwOeYu9noMClEiUawi2mJebh1lpZgUBY3A8hWbnaYsbQ\r\n" + 
			"pm+LFwECgYEA+YTAn21iPXI9pMu+vUaRt3Cyt4/3gPhjw0AZbaTtOaSrapsiFLac\r\n" + 
			"Ia9VLmsqQd4W06njxDk/Alq8DdYrtKWPNSOeh442z15NZ0vaEoinqE93vDZee7ZD\r\n" + 
			"ypfruYryQBZWfiGtO3yEhqD8P9iDT2cziFS0j20ctt0hKQULCw6ARuECgYEA0EFw\r\n" + 
			"16LqdZZSj7UfSAazIDZRvvd4s0pOtJrw+mbtq9NElsLJqJGApUJt5PS/LWuO/kT7\r\n" + 
			"5JuEHMbq+jkvoOkbqU65hgznytrbc6Q+bAQT7FcBVjJyJbVqXaYuBofbPn0YPun2\r\n" + 
			"BkqnRdKj3r4/Q2RkZQ86R85p2t8bKS2PYujol8kCgYEA5ov8ltIwtWm6/2uqP6TW\r\n" + 
			"utIMr4L8Q1Q+iYfOdvqunlZCdGwGn3Di7lTctdka4GVeXhBZfVMPnxNLMtWy6GUY\r\n" + 
			"3xacQc/rYMtXZZw2UG2ny+IxVdw+jLwqAyVADt4NW53IKR7gu1gf820H5c4d9d37\r\n" + 
			"RXpOoz9ECspi+03HmF27tMECgYEAs8LpHpl3/IwHNBjJqahu0i1RHqAKxegsS+U6\r\n" + 
			"gUp7eUfh+qik3SbLftr6dRl2NESVp5HwRIKg5R4nElpubF0LDXfw2G8nXYjQyN9L\r\n" + 
			"Kn3SnLz1YGwm77nK2I9rRyPZ8fSVCyl0vjWOcczCurBJd2ie6ETer8DSyqSukzrK\r\n" + 
			"fDxDGVECgYEAr4Mi07d/lAT/xrpTl8yqyByWj7sTcrFm5Lqlbk0PmiIC1fvkVLQ+\r\n" + 
			"AwszRvuRZhPjFYyKjvSy9FPjIZCouAbcxswHLuUU5fjZ4H0QKNIhKY4dy/lrme5C\r\n" + 
			"d+6fqC8evm1qukL+0KEbzbmRAgn+8NPUa04INi73zVt451KPVmQSxAU=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyvulMrLopXdpt5rHCmVJ\r\n" + 
			"EXKWiV37pM9r1iPH/ZbLF9t/dQownFG8yB/qGsIkXme5AckKxVuTQeAprq54EkEF\r\n" + 
			"hbaNcV5nJxEdwz0HGLKAeqgcUiYfNkBiw8IhBvnf2AFdJaskGyL9jP2K9NjzKXjU\r\n" + 
			"LFiceg8TADWnFB+nJygCJVow3p3xWIBtKglsDkFrAKRTyk4Q0ztY1tMJVas5KYsQ\r\n" + 
			"Empx8dgGNlRLvOThfAVFbWieJyIhnCLCQwPMNTIN5+uR03//1ybH2Y3y7NGI6kUH\r\n" + 
			"3Mh9mamFeOrT2UioVWh05GbIu9Y8Xs6ekbNlUzigZEbxVpEaJgB45MXo4mbaMG5d\r\n" + 
			"qQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
