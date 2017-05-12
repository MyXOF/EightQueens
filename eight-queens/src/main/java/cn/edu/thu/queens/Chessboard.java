package cn.edu.thu.queens;


public class Chessboard {
	public byte[] pos;

	public Chessboard(int[] pos) {
		this.pos = new byte[pos.length];
		for(int i = pos.length - 1; i >= 0;i--){
			this.pos[i] = (byte) pos[i];
		}
	}

	@Override
	public int hashCode() {
		return pos[0];
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chessboard other = (Chessboard) obj;
		if(other.pos.length != this.pos.length) return false;
		for(int i = this.pos.length-1; i >= 0; i--){
			if((other.pos[i] ^ this.pos[i]) != 0) return false;
		}
		return true;
	}
}
