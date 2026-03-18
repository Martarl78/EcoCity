# EcoCity
Proyecto EcoCity

🏙️ Simulador de Ciudad por Consola
Proyecto de simulación donde el jugador gestiona una ciudad construyendo edificios, administrando recursos y enfrentándose a eventos climáticos que afectan su desarrollo.

🎯 Objetivo del Proyecto
Crear un motor de juego por consola que permita:
• 	Construir distintos tipos de edificios.
• 	Gestionar recursos como energía, agua y dinero.
• 	Mantener la salud de las estructuras.
• 	Superar eventos aleatorios que impactan la ciudad.

🧩 Funcionalidades Principales
• 	Sistema de edificios con comportamientos diferentes.
• 	Producción y consumo de recursos cada mes.
• 	Balance energético que afecta la felicidad de la ciudad.
• 	Eventos climáticos que dañan edificios o modifican recursos.
• 	Excepciones personalizadas para situaciones como falta de fondos.
• 	Uso de colecciones para almacenar edificios y recursos.

🔄 Ciclo de Simulación
Cada mes el motor:
1. 	Recorre todos los edificios.
2. 	Calcula producción y consumo de energía.
3. 	Ajusta felicidad según el balance.
4. 	Aplica un evento aleatorio.
5. 	Actualiza la salud y efectos de cada edificio.

⚠️ Excepciones
• 	FondosInsuficientesException: se lanza al intentar construir sin dinero.
• 	Riesgo de explosión en centrales nucleares si su salud es demasiado baja.
