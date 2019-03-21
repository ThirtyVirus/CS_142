/**
 * Created by danielshen on 4/25/17.
 */

class SportsComplex {

	public int maxArenas;
	public int arenas;

	/**
	 * need to do later
	 */
	public SportsComplex(int maxArenas) {
		/**
		 * Initializes the Sports Complex.

		 Parameters:
		 maxInUse - the maximum number of arenas
		 (battles that can take place
		 at the same time) in the sports
		 complex
		 */

		arenas = 0;
		this.maxArenas = maxArenas;

	}

	public synchronized void enterArena(WoolieBattleThread t){
		/**
		 * Battle thread attempts to enterArena an arena.
		 If an arena is available the thread enters the arena and the battle begins.
		 If no arena is available, the threads waits to be notified that an arena is free.
		 Only one battle at a time can request an arena.
		 Parameters: t - The battle thread requesting an arena.
		 */

		while (arenas >= maxArenas){

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			arenas = arenas + 1;
		}
	}

	public synchronized void leaveArena(){
		/**
		 * When a battle is complete the battle will
		 notify the arena that the arena is free.
		 The arena will let any battles waiting know
		 Only one battle at a time can exit and arena. there is a free arena.
		 */

        notifyAll();

		arenas = arenas - 1;

	}
}
